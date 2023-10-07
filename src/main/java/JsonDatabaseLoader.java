import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.PersonEntity;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;

@NoArgsConstructor
public class JsonDatabaseLoader {

    public void fromJsonToDatabase() {

        String jsonFilePath = "/Users/tomas/Java/SDA/ISS/src/iss.json";

        try {
            Session session = DbConnect.getSession();
            Transaction transaction = session.beginTransaction();


            // Otevření JSON souboru pro čtení
            BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
            JsonParser jsonParser = new JsonParser();

            // Parse JSON data
            JsonElement jsonElement = jsonParser.parse(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonArray peopleArray = jsonObject.getAsJsonArray("people");

                // Iterace přes JSON pole a uložení dat do databáze
                for (JsonElement personElement : peopleArray) {
                    JsonObject personObject = personElement.getAsJsonObject();
                    String name = personObject.get("name").getAsString();
                    String craft = personObject.get("craft").getAsString();

                    PersonEntity person = new PersonEntity();
                    person.setName(name);
                    person.setCraft(craft);

                    session.save(person);
                }

                transaction.commit();
                session.close();
                System.out.println("Data were loaded to the database.");
            } else {
                System.out.println("JSON file is not Object.");
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
