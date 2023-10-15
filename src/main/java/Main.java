import com.google.gson.JsonElement;
import database.DbOperations;
import entity.ISSPositionEntity;
import json.JsonPeople;
import json.JsonPosition;
import json.JsonWorker;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


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

        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the astronaut to delete (or press Enter to delete the entire database): ");
        String astronautName = scanner.nextLine();

        if (astronautName.isEmpty()) {
            // If the input is empty, delete the entire database
            DbOperations.deleteDatabase();
            System.out.println("Entire database has been deleted.");
        } else {
            // Otherwise, delete the astronaut by name
            DbOperations.deletePersonByName(astronautName);
            System.out.println("Astronaut " + astronautName + " has been deleted.");
        }

        // Close the scanner
        scanner.close();
    }
}

