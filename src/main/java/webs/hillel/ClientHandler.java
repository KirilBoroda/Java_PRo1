package webs.hillel;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private String clientName;
    private Socket clientSocket;
    private InputStream input;
    private OutputStream output;
    private Server server;

    public ClientHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();

            byte[] nameBuffer = new byte[1024];
            int nameBytesRead = input.read(nameBuffer);
            String clientName = new String(nameBuffer, 0, nameBytesRead).trim();

            sendMessage("Welcome to the server, " + clientName + "!");


            broadcastMessage("[SERVER] " + clientName + " connected.");

            while (true) {
                byte[] buffer = new byte[1024];
                int bytesRead = input.read(buffer);

                if (bytesRead == -1) {
                    break;
                }

                String receivedMessage = new String(buffer, 0, bytesRead).trim();

                if (receivedMessage.equals("-exit")) {

                    break;
                } else if (receivedMessage.startsWith("-file")) {

                    String filePath = receivedMessage.substring(6).trim();
                    handleFileTransfer(filePath);
                } else {
                    String message = "[" + clientName + "] " + receivedMessage;
                    broadcastMessage(message);
                }
            }

            server.removeClient(this);

            broadcastMessage("[SERVER] " + clientName + " disconnected.");

            clientSocket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) throws IOException {
        if (output != null) {
            output.write(message.getBytes());
            output.flush();
        }
    }

    public void broadcastMessage(String message) throws IOException {
        System.out.println(message);

        for (ClientHandler client : server.getClients()) {
            if (client != this) {

                client.sendMessage(message);
            }
        }
    }

    private void handleFileTransfer(String fileName) throws IOException {
        String filePath = "C:\\Users\\kiril\\IdeaProjects\\Java_PRo\\" + fileName.replace("/", "\\");
        File file = new File(filePath);

        if (file.createNewFile()) {
            System.out.println("Receiving file: " + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            fileOutputStream.close();

            System.out.println("File received: " + fileName);
        } else {
            System.out.println("Failed to create file: " + fileName);
        }
    }
}
