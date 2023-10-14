package database;

import entity.PersonEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class KozmonautCounter {

    public static int spocitajKozmonautov() {
        Session session = DbConnect.getSession();
        try {
            session.beginTransaction();
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM PersonEntity");
            long pocet = query.getSingleResult();
            session.getTransaction().commit();
            return (int) pocet;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
    }
}
