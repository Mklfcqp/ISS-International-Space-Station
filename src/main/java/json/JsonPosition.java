package json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.DbConnect;
import entity.ISSPositionEntity;
import entity.PersonEntity;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

@NoArgsConstructor
public class JsonPosition implements JsonWorker {

    @Override
    public JsonElement jsonParser(String jsonFilePath) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(reader);
        reader.close();
        return jsonElement;
    }

    @Override
    public void jsonLoaderToDatabase(JsonElement jsonElement) {

        try {
            Session session = DbConnect.getSession();
            Transaction transaction = session.beginTransaction();

            // Test jestli je JSON Object
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonArray positionArray = jsonObject.getAsJsonArray("iss_position");

                // Iterace přes JSON pole a uložení dat do databáze
                for (JsonElement positionElement : positionArray) {
                    JsonObject positionObject = positionElement.getAsJsonObject();
                    double longitude = positionObject.get("longitude").getAsDouble();
                    double latitude = positionObject.get("latitude").getAsDouble();
                    long timestamp = positionObject.get("timestamp").getAsLong();

                    //String time = fromTimestampToDate(timestamp);
                    
                    ISSPositionEntity position = new ISSPositionEntity();
                    position.setLongitude(longitude);
                    position.setLatitude(latitude);
                    position.setTimestamp(timestamp);
                    //position.setTime(time);

                    session.save(position);
                }

                transaction.commit();
                session.close();
                System.out.println("Data were loaded to the database.");
            } else {
                System.out.println("JSON file is not Object.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
