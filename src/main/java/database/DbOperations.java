package database;

import entity.ISSPositionEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DbOperations {


    public List<ISSPositionEntity> ISSPositionDataToList() {

        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        List<ISSPositionEntity> positionsFromDatabase = session.createQuery("From ISSPositionEntity").list();
        List<ISSPositionEntity> positions = positionsFromDatabase;

        transaction.commit();
        session.close();

        return positions;
    }
    public static int countCosmonauts() {
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
