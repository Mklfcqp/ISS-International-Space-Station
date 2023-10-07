import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;


public class JsonReader {

    public static void main(String[] args) {

        String jsonFilePath = "/Users/tomas/Java/SDA/ISS/src/iss.json";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));

            JsonParser jsonParser = new JsonParser();

            JsonElement jsonElement = jsonParser.parse(reader);

            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                JsonArray peopleArray = jsonObject.getAsJsonArray("people");

                for (JsonElement personElement : peopleArray) {
                    JsonObject personObject = personElement.getAsJsonObject();
                    String name = personObject.get("name").getAsString();
                    String craft = personObject.get("craft").getAsString();
                    System.out.println("Name: " + name + ", Craft: " + craft);
                }

                int number = jsonObject.get("number").getAsInt();
                String message = jsonObject.get("message").getAsString();

                System.out.println("Number: " + number);
                System.out.println("Message: " + message);
            } else {
                System.out.println("JSON file is not Object");
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
