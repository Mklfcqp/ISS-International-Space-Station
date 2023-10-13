import com.google.gson.JsonElement;
import json.JsonPeople;
import json.JsonPosition;
import json.JsonWorker;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) throws IOException {

        String jsonFilePathPeople = "/Users/tomas/Java/SDA/ISS/src/iss.json";
        String jsonFilePathPosition = "/Users/tomas/Java/SDA/ISS/src/issposition.json";

        JsonWorker jsonPeople = new JsonPeople();
        JsonWorker jsonPosition = new JsonPosition();

        JsonElement jsonElementPeople = jsonPeople.jsonParser(jsonFilePathPeople);
        jsonPeople.jsonLoaderToDatabase(jsonElementPeople);

        JsonElement jsonElementPosition = jsonPosition.jsonParser(jsonFilePathPosition);
        jsonPosition.jsonLoaderToDatabase(jsonElementPosition);


    }

}
