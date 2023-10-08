import com.google.gson.JsonElement;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String jsonFilePathPeople = "/Users/tomas/Java/SDA/ISS/src/iss.json";
        String jsonFilePathPosition = "/Users/tomas/Java/SDA/ISS/src/issposition.json";

        JsonWorker jsonWorker = new JsonWorker();

        JsonElement jsonElementPeople = jsonWorker.jsonParser(jsonFilePathPeople);
        jsonWorker.jsonPersonLoaderToDatabase(jsonElementPeople);

    }

}
