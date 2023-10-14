package database;

import entity.ISSPositionEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

}
