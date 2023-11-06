package api;

import com.google.gson.JsonParser;
import database.DbConnect;
import entity.ISSPositionEntity;
import entity.PersonEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class ISSPositionAPI implements APILoaderToDatabase {

    @Override
    public void apiLoaderToDatabase() {

        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            URL url = new URL("http://api.open-notify.org/iss-now.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponse code: " + responseCode);
            } else {
                InputStream inputStream = connection.getInputStream();
                JSONTokener tokener = new JSONTokener(inputStream);
                JSONObject jsonObject = new JSONObject(tokener);

                JSONObject issPositionObject = jsonObject.getJSONObject("iss_position");
                double latitude = issPositionObject.getDouble("latitude");
                double longitude = issPositionObject.getDouble("longitude");
                long timestamp = jsonObject.getLong("timestamp");
                String time = fromTimestampToDate(timestamp);

                ISSPositionEntity issPosition = new ISSPositionEntity();
                issPosition.setLatitude(latitude);
                issPosition.setLongitude(longitude);
                issPosition.setTime(time);

                session.save(issPosition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        transaction.commit();
        session.close();
        System.out.println("Data were loaded to the database.");
    }


    public void apiCurrentPosition() {
        try {
            URL url = new URL("http://api.open-notify.org/iss-now.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponse code: " + responseCode);
            } else {
                // Získání vstupního proudu z API
                InputStream inputStream = connection.getInputStream();
                JSONTokener tokener = new JSONTokener(inputStream);
                JSONObject jsonObject = new JSONObject(tokener);

                // Získání informace o aktuální poloze
                JSONObject issPositionObject = jsonObject.getJSONObject("iss_position");
                double latitude = issPositionObject.getDouble("latitude");
                double longitude = issPositionObject.getDouble("longitude");
                long timestamp = jsonObject.getLong("timestamp");
                String time = fromTimestampToDate(timestamp);

                System.out.println("Current position of ISS:");
                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);
                System.out.println("Timestamp: " + timestamp);
                System.out.println("Time: " + time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fromTimestampToDate(long timestamp) {
        Date currentDate = new Date(timestamp * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = dateFormat.format(currentDate);
        return date;
    }
}