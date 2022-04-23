package administrator;

import model.City;

public class DistanceCalculator {
    private static final double EARTH_RADIUS = 6371;

    private DistanceCalculator() {}

    public static double computeDistance(City firstCity, City secondCity) {
        double lat1 = firstCity.getLatitude();
        double lon1 = firstCity.getLongitude();

        double lat2 = secondCity.getLatitude();
        double lon2 = secondCity.getLongitude();

        // Haversine formula
        double deltaLon = lon2 - lon1;
        double deltaLat = lat2 - lat1;
        double result = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(deltaLon / 2),2);

        double secondResult = 2 * Math.asin(Math.sqrt(result));
        return(secondResult * EARTH_RADIUS);
    }
}
