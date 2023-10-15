package database;

import entity.ISSPositionEntity;
import entity.PersonEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DbOperations {

    public List<ISSPositionEntity> ISSPositionDataToList() {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        List<ISSPositionEntity> positionsFromDatabase = session.createQuery("FROM ISSPositionEntity").list();
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
            long count = query.getSingleResult();
            session.getTransaction().commit();
            return (int) count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
    }

    public static void deleteDatabase() {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        // Delete all records in PersonEntity
        Query deletePeopleQuery = session.createQuery("DELETE FROM PersonEntity");
        int deletedPeople = deletePeopleQuery.executeUpdate();

        // Delete all records in ISSPositionEntity
        Query deletePositionQuery = session.createQuery("DELETE FROM ISSPositionEntity");
        int deletedPositions = deletePositionQuery.executeUpdate();

        transaction.commit();
        session.close();

        System.out.println("Deleted " + deletedPeople + " people records.");
        System.out.println("Deleted " + deletedPositions + " position records.");
    }

    public static void deletePersonByName(String name) {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        Query deletePersonQuery = session.createQuery("DELETE FROM PersonEntity WHERE name = :name");
        deletePersonQuery.setParameter("name", name);

        int deletedCount = deletePersonQuery.executeUpdate();
        transaction.commit();
        session.close();

        System.out.println("Deleted " + deletedCount + " person records with name " + name);
    }
}