package webs.hillel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    private final SessionFactory sessionFactory;

    public StudentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add null student.");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to add student.", e);
        }
    }

    public void updateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot update null student.");
        }

        if (student.getId() == null) {
            throw new IllegalArgumentException("Cannot update student without an ID.");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update student.", e);
        }
    }

    public void deleteStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot delete null student.");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to delete student.", e);
        }
    }

    public List<Student> getAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve all students.", e);
        }
    }

    public Student getStudentById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot retrieve student with null ID.");
        }

        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve student by ID.", e);
        }
    }

}
