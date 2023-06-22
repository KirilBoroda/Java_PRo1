package webs.hillel;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Student.class);

            return configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to build the SessionFactory: " + e.getMessage(), e);
        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
