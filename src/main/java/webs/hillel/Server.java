package webs.hillel;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    private static final int PORT = 8888;
    private List<ClientHandler> clients;

    public Server() {
        clients = new ArrayList<>();
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

}
