package webs.hillel;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        // Adding records
        studentDAO.addStudent(new Student("John Doe", "john@example.com"));
        studentDAO.addStudent(new Student("Jane Smith", "jane@example.com"));
        studentDAO.addStudent(new Student("Mike Johnson", "mike@example.com"));

        // Retrieving all records
        List<Student> students = studentDAO.getAllStudents();
        logger.info("All Students:");
        for (Student student : students) {
            logger.info(student.getId() + ": " + student.getName() + " - " + student.getEmail());
        }

        // Retrieving a record by ID
        Long studentId = 1L;
        Student student = studentDAO.getStudentById(studentId);
        if (student != null) {
            logger.info("Student with ID " + studentId + ": " + student.getName() + " - " + student.getEmail());
        } else {
            logger.info("Student with ID " + studentId + " not found.");
        }

        // Updating a record
        if (student != null) {
            student.setEmail("new-email@example.com");
            studentDAO.updateStudent(student);
            logger.info("Updated Student with ID " + studentId + ": " + student.getName() + " - " + student.getEmail());
        }

        // Deleting a record
        if (student != null) {
            studentDAO.deleteStudent(student);
            logger.info("Deleted Student with ID " + studentId);
        }

        // Checking after deletion
        Student deletedStudent = studentDAO.getStudentById(studentId);
        if (deletedStudent == null) {
            logger.info("Student with ID " + studentId + " is deleted.");
        }
    }
}
