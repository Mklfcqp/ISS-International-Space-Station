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

        String fileNamePeople = "iss.json";
        String fileNamePosition = "issposition.json";

        JsonWorker jsonPeople = new JsonPeople();
        JsonWorker jsonPosition = new JsonPosition();

        JsonElement jsonElementPeople = jsonPeople.jsonParser("src/" + fileNamePeople);
        jsonPeople.jsonLoaderToDatabase(jsonElementPeople);

        JsonElement jsonElementPosition = jsonPosition.jsonParser("src/" + fileNamePosition);
        jsonPosition.jsonLoaderToDatabase(jsonElementPosition);

//        DbOperations operations = new DbOperations();
//        List<ISSPositionEntity> iss = operations.ISSPositionDataToList();




    }

}
