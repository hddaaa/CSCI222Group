package util.common;

/**
 * Created by hdd on 16/05/15.
 */
public class MathUtil {
    public static final double PRICE_PER_KM = 0.2;
    private static final double EARTH_RADIUS = 6371.004;// r of earth

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static double getPriceFloat(String fareClass, String agent) {
        double r = 1;
        switch (fareClass) {
            case "F":
                r = r * 1.6;
                break;
            case "B":
                r = r * 1.4;
                break;
            case "PE":
                r = r * 1.2;
                break;
            default:
                r = r * 1;
                break;
        }
        if (agent != null & agent.equals("")) {
            r = r * 0.85;
        }
        return r;
    }
}
