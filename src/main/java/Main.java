import api.APILoaderToDatabase;
import api.ISSPositionAPI;
import api.SpacePeopleAPI;
import database.DbOperations;
import entity.ISSPositionEntity;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        //nahrani lidi ve vesmiru do databaze
//        APILoaderToDatabase people = new SpacePeopleAPI();
//        people.apiLoaderToDatabase();

//        ISSPositionAPI position = new ISSPositionAPI();
//
//        DbOperations operations = new DbOperations();
//        operations.deletePositionDatabase();
//        
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        //nahrani aktualni polohy do databaze 2x
//        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> {
//            position.apiLoaderToDatabase();
//        }, 0, 5, TimeUnit.SECONDS);
//
//
//        executor.schedule(() -> {
//            scheduledFuture.cancel(true);
//            executor.shutdown();
//        }, 5, TimeUnit.SECONDS);


//        ISSPositionOperations positionOperations = new ISSPositionOperations();        
//        //aktualni poloha
//        positionOperations.apiCurrentPosition();
//        positionOperations.ISSspeed();
        
        positionOperations.
        positionOperations.
        positionOperations.
        positionOperations.
        positionOperations.



    }

}
