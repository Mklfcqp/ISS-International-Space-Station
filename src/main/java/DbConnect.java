import entity.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbConnect {

    public static Session getSession() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(PersonEntity.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}
