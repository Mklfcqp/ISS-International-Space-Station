package database;

import entity.ISSPositionEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ISSPositionOperations {
        
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
/*
    private String fromTimestampToDate(long timestamp) {
        Date currentDate = new Date(timestamp * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = dateFormat.format(currentDate);
        return date;
    }
*/    
    private LocalDateTime convertTimestampToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));
        return localDateTime;
    }

    private LocalDateTime findLocalDateTimeById(int id) {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();

        LocalDateTime localDateTime = session.get(ISSPosition.class, id);
        return localDateTime;

        transaction.commit();
        session.close();
    }

    private LocalTime convertToLocalTime(int id) {
        LocalDateTime localDateTime = findLocalDateTimeById(id);
        LocalTime localTime = localDateTime.toLocalTime(); 
        return localTime;
    }

    private double secondsDifference(int id2, int id1) {,
        LocalTime time1 = convertToLocalTime(id1);
        LocalTime time2 = convertToLocalTime(id2);
        double difference = ChronoUnit.SECONDS.between(time2, time1);
        return difference;
    }

    private double findLatitudeById(int id) {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();
        
        double latitude = session.get(ISSPosition.class, id);
        return latitude;

        transaction.commit();
        session.close();
    }

    private double findLongitudeById(int id) {
        Session session = DbConnect.getSession();
        Transaction transaction = session.beginTransaction();
        
        double longitude = session.get(ISSPosition.class, id);
        return longitude;

        transaction.commit();
        session.close();
    }
        
    public void ISSspeed() {

        double lat1 = findLatitudeById(1);
        double long1 = findLongitudeById(1);

        double lat2 = findLatitudeById(2);
        double long2 = findLongitudeById(2);

        //vzorec pro drahu
        double earthRadius = 6371000;
        double latitudeRad1 = Math.toRadians(lat1.getLatitude());
        double longitudeRad1 = Math.toRadians(long1.getLongitude());
        double latitudeRad2 = Math.toRadians(lat2.getLatitude());
        double longitudeRad2 = Math.toRadians(long2.getLongitude());

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
        double time = -secondsDifference(2, 1);

        //vzorec pro rychlost
        double speedMS = draha / time;
        double speedKMS = 3.6 * speedMS;
        System.out.println(speedKMS);
    }
}
