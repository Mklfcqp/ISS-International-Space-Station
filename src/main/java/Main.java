import com.google.gson.JsonElement;
import database.DbOperations;
import entity.ISSPositionEntity;
import json.JsonPeople;
import json.JsonPosition;
import json.JsonWorker;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String jsonFilePathPeople = "iss.json";
        String jsonFilePathPosition = "issposition.json";

        JsonWorker jsonPeople = new JsonPeople();
        JsonWorker jsonPosition = new JsonPosition();

        JsonElement jsonElementPeople = jsonPeople.jsonParser("src/" + jsonFilePathPeople);
        jsonPeople.jsonLoaderToDatabase(jsonElementPeople);

        JsonElement jsonElementPosition = jsonPosition.jsonParser("src/" + jsonFilePathPosition);
        jsonPosition.jsonLoaderToDatabase(jsonElementPosition);

        // Get the number of astronauts
        int astronautCount = DbOperations.countCosmonauts();

        System.out.println("Total number of astronauts: " + astronautCount);
    }
}
