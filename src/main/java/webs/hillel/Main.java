package webs.hillel;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        StudentDAO studentDAO = new StudentDAO(sessionFactory);

        studentDAO.addStudent(new Student("John Doe", "john@example.com"));
        studentDAO.addStudent(new Student("Jane Smith", "jane@example.com"));
        studentDAO.addStudent(new Student("Mike Johnson", "mike@example.com"));

        List<Student> students = studentDAO.getAllStudents();
        LOGGER.info("All Students:");
        for (Student student : students) {
            LOGGER.info(student.getId() + ": " + student.getName() + " - " + student.getEmail());
        }

        Long studentId = 1L;
        Student student = studentDAO.getStudentById(studentId);
        if (student != null) {
            LOGGER.info("Student with ID " + studentId + ": " + student.getName() + " - " + student.getEmail());
        } else {
            LOGGER.info("Student with ID " + studentId + " not found.");
        }

        if (student != null) {
            student.setEmail("new-email@example.com");
            studentDAO.updateStudent(student);
            LOGGER.info("Updated Student with ID " + studentId + ": " + student.getName() + " - " + student.getEmail());
        }

        if (student != null) {
            studentDAO.deleteStudent(student);
            LOGGER.info("Deleted Student with ID " + studentId);
        }

        Student deletedStudent = studentDAO.getStudentById(studentId);
        if (deletedStudent == null) {
            LOGGER.info("Student with ID " + studentId + " is deleted.");
        }
    }
}
