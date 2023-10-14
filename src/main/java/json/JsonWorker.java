package json;

import com.google.gson.JsonElement;

import java.io.IOException;

public interface JsonWorker {

     JsonElement jsonParser(String jsonFilePath) throws IOException;

     void jsonLoaderToDatabase(JsonElement jsonElement);

     void loadFromApi(String url) throws IOException, InterruptedException;

}
