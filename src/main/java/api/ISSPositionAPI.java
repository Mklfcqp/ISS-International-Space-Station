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
                String time = convertTimestampToLocalDateTime(timestamp);

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
    
    private LocalDateTime convertTimestampToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
        return localDateTime;
    }

    private LocalDateTime findTimeById(int id) {
        String date = session.get(ISSPosition.class, id);
        return date;
    }

    private LocalTime convertToLocalTime(int id) {
        LocalDateTime localDateTime = findTimeById(id);
        LocalTime localTime = localDateTime.toLocalTime(); 
        return localTime;
    }

    

    public void ISSspeed() {

        
        LocalDateTime positionID2 = findTimeById(2);

        // Extrahování pouze času z LocalDateTime
        
        LocalTime time2 = positionID2.toLocalTime(); 

        
        Location l1 = new Location(50.1412, -51.6531, t1);
        Location l2 = new Location(49.6675, -49.0980, t2);



        double sekundyRozdil = ChronoUnit.SECONDS.between(time2, time1);

        //vzorec pro drahu
        double earthRadius = 6371000;
        double latitudeRad1 = Math.toRadians(l1.getLatitude());
        double longitudeRad1 = Math.toRadians(l1.getLongitude());
        double latitudeRad2 = Math.toRadians(l2.getLatitude());
        double longitudeRad2 = Math.toRadians(l2.getLongitude());

        double zavorka1 = ((latitudeRad1 - latitudeRad2)/2);
        double zavorka2 = ((longitudeRad1 - longitudeRad2)/2);

        double mezivypocet1 = 2 * earthRadius;
        double mezivypocet2 = Math.pow(Math.sin(zavorka1), 2);
        double mezivypocet3 = Math.pow(Math.sin(zavorka2), 2);
        double mezivypocet4 = Math.cos(latitudeRad1);
        double mezivypocet5 = Math.cos(latitudeRad2);
        double mezivypocet6 = mezivypocet2 + mezivypocet4 * mezivypocet5 * mezivypocet3;
        double mezivypocet7 = Math.sqrt(mezivypocet6);
        double mezivypocet8 = Math.asin(mezivypocet7);

        double draha = mezivypocet1 * mezivypocet8;
        System.out.println("Draha je: " +draha);

        //vzorec pro cas
        double time = -sekundyRozdil;

        //vzorec pro rychlost
        double speedMS = draha / time;
        double speedKMS = 3.6 * speedMS;
        System.out.println(speedKMS);

    }

    }
}
