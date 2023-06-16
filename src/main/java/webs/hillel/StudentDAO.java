package webs.hillel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {
    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());

    private final SessionFactory sessionFactory;

    public StudentDAO() {
        sessionFactory = HibernateSession.getSessionFactory();
    }

    public void addStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Failed to add student: " + e.getMessage(), e);
        }
    }

    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Failed to update student: " + e.getMessage(), e);
        }
    }

    public void deleteStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.log(Level.SEVERE, "Failed to delete student: " + e.getMessage(), e);
        }
    }

    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to retrieve all students: " + e.getMessage(), e);
        }
        return null;
    }

    public Student getStudentById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to retrieve student by ID: " + e.getMessage(), e);
        }
        return null;
    }
}
