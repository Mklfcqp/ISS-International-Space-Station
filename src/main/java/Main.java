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
//        //aktualni poloha
//        position.apiCurrentPosition();
//
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        //nahrani aktualni polohy do databaze kazdych 5 sekund po dobu 20 sekund
//        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(() -> {
//            position.apiLoaderToDatabase();
//        }, 0, 5, TimeUnit.SECONDS);
//
//
//        executor.schedule(() -> {
//            scheduledFuture.cancel(true);
//            executor.shutdown();
//        }, 20, TimeUnit.SECONDS);

        DbOperations operations = new DbOperations();
        List<ISSPositionEntity> positions = operations.ISSPositionDataToList();
        for(ISSPositionEntity e : positions) {
            System.out.println(e);
        }









//        DbOperations operations = new DbOperations();
//        List<ISSPositionEntity> iss = operations.ISSPositionDataToList();




    }

}
