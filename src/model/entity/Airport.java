package model.entity;

/**
 * Created by hdd on 12/05/15.
 */
public class Airport extends Entity {
    private String name;
    private String city;
    private String country;
    private String IATA_FAA;
    private String latitude;
    private String longitude;
    private String altitude;
    private String timezone;
    private String DST;
    private String database_timezone;

    public Airport() {
    }

    public Airport(String name, String city, String country, String IATA_FAA, String latitude, String longitude, String altitude, String timezone, String DST, String database_timezone) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.IATA_FAA = IATA_FAA;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.timezone = timezone;
        this.DST = DST;
        this.database_timezone = database_timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIATA_FAA() {
        return IATA_FAA;
    }

    public void setIATA_FAA(String IATA_FAA) {
        this.IATA_FAA = IATA_FAA;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getDST() {
        return DST;
    }

    public void setDST(String DST) {
        this.DST = DST;
    }

    public String getDatabase_timezone() {
        return database_timezone;
    }

    public void setDatabase_timezone(String database_timezone) {
        this.database_timezone = database_timezone;
    }
}
