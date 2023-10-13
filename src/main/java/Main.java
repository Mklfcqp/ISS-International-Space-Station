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


//        long timestamp = 1696751354L;
//
//        Date currentDate = new Date(timestamp * 1000);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        String date = dateFormat.format(currentDate);
//        System.out.println(date);

        
//        long timestamp = 1696751354L;
//
//        String date = fromTimestampToDate(timestamp);
//        System.out.println(date);

//    public static String fromTimestampToDate(long timestamp) {
//        Date currentDate = new Date(timestamp * 1000);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        String date = dateFormat.format(currentDate);
//        return date;
//    }

    }

}
