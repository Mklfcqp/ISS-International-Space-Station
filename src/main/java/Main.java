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

//        ISSPositionAPI position = new ISSPositionAPI()
            // aktualni poloha
//        position.apiCurrentPosition();
//        position.ISSspeed();
//
//        DbOperations dbOperations = new DbOperations();
//        dbOperations.deletePositionDatabase();
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


//        dbOperations.printAllCrafts();
//        dbOperations.printAllPeople();
//        dbOperations.printAllPeopleByCraft(String craftName);
//        dbOperations.printPersonByName(String personName);
//        dbOperations.addNewCraft(String craftName);
//        dbOperations.addPersonAndItsCraft(String personName, String craftName);
//        dbOperations.updateCraftName(String oldName, String newName);
//        dbOperations.updatePersonName(String oldName, String newName);
//        dbOperations.updatePersonCraft(String personName, String craftName);
//        dbOperations.deleteCraftByName(String craftName);
//        dbOperations.deletePersonByName(String personName);
//        dbOperations.deleteAllPeople();
//        dbOperations.deleteAllCrafts();
//        dbOperations.deleteAllPositions();
    
    }

}
