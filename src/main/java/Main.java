import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose data source:");
        System.out.println("1. Load from API");
        System.out.println("2. Load from file");
        System.out.print("Enter your choice (1/2): ");

        int choice = scanner.nextInt();

        if (choice == 1 {
            System.out.print("Enter the API URL: "); // http://api.open-notify.org/astros.json
            String apiUrl = scanner.next();
            loadFromApi(apiUrl);
        } else if (choice == 2) {
            System.out.print("Enter the file name: "); // iss.json
            String filePath = scanner.next();
            loadFromFile("src/" + filePath);
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        scanner.close();
    }

    private static void loadFromFile(String filePath) throws IOException {
        JsonWorker jsonWorker = new JsonWorker();

        JsonElement jsonElementPeople = jsonWorker.jsonParser(filePath);
        jsonWorker.jsonPersonLoaderToDatabase(jsonElementPeople);
    }

    private static void loadFromApi(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonWorker jsonWorker = new JsonWorker();
            jsonWorker.jsonPersonLoaderToDatabase(jsonObject);
        } else {
            System.out.println("HTTP request failed with status code: " + response.statusCode());
        }
    }
}