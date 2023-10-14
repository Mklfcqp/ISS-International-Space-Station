import com.google.gson.JsonElement;
import json.JsonPeople;
import json.JsonPosition;
import json.JsonWorker;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

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

        // test pull request




    }

}
