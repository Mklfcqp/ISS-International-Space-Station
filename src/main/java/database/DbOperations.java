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

    public void saveToVariables() {

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            // Načtení dat z databáze
            List<ISSPositionEntity> positions = session.createQuery("from PositionEntity", ISSPositionEntity.class).list();

            // Procházení načtených dat a uložení do proměnných
            for (ISSPositionEntity position : positions) {
                int id = position.getId();
                double latitude = position.getLatitude();
                double longitude = position.getLongitude();
                Date timestamp = position.getTimestamp();
                int status = position.getStatus();

                // Zde můžete provést operace s načtenými daty
                System.out.println(id + "\t" + latitude + "\t" + longitude + "\t" + timestamp + "\t" + status);
            }

            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }


    public void ISSspeed() {

        //rychlost = draha / cas
        //draha =
        //cas = rozdil mezi dvema casy

        /*
                        zemepisna sirka         zemepisna delka          zemepisna sirka(rad)       zemepisna delka(rad)
        bod 1           latitude                longitude
        bod 2           letitude                longitude
        polomer zeme    6371000m
                        6371km
         */

    }

}
