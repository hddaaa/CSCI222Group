package model.dao;

import model.entity.Airport;
import model.entity.Entity;
import util.DB.DBConnection;
import util.common.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdd on 12/05/15.
 */
public class AirportDao implements DaoInterface {
    @Override
    public Entity getEntity(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM airport WHERE id=? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Airport airport = new Airport();
            if (rs.next()) {
                airport.setId(rs.getInt("id"));
                airport.setName(rs.getString("name"));
                airport.setCity(rs.getString("city"));
                airport.setCountry(rs.getString("country"));
                airport.setIATA_FAA(rs.getString("IATA_FAA"));
                airport.setLatitude(rs.getString("latitude"));
                airport.setLongitude(rs.getString("longitude"));
                airport.setAltitude(rs.getString("altitude"));
                airport.setTimezone(rs.getString("timezone"));
                airport.setDST(rs.getString("DST"));
                airport.setDatabase_timezone(rs.getString("database_timezone"));
                return airport;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("AirportDao: getEntity");
    }

    public Airport getAirport(String cityCode) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM airport WHERE IATA_FAA=? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cityCode);
            ResultSet rs = preparedStatement.executeQuery();
            Airport airport = new Airport();
            if (rs.next()) {
                airport.setId(rs.getInt("id"));
                airport.setName(rs.getString("name"));
                airport.setCity(rs.getString("city"));
                airport.setCountry(rs.getString("country"));
                airport.setIATA_FAA(rs.getString("IATA_FAA"));
                airport.setLatitude(rs.getString("latitude"));
                airport.setLongitude(rs.getString("longitude"));
                airport.setAltitude(rs.getString("altitude"));
                airport.setTimezone(rs.getString("timezone"));
                airport.setDST(rs.getString("DST"));
                airport.setDatabase_timezone(rs.getString("database_timezone"));
                return airport;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("AirportDao: getAirport");
    }

    @Override
    public List<Entity> getAllEntity() throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM airport";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Entity> list = new ArrayList<Entity>();
            while (rs.next()) {
                Airport airport = new Airport();
                airport.setId(rs.getInt("id"));
                airport.setName(rs.getString("name"));
                airport.setCity(rs.getString("city"));
                airport.setCountry(rs.getString("country"));
                airport.setIATA_FAA(rs.getString("IATA_FAA"));
                airport.setLatitude(rs.getString("latitude"));
                airport.setLongitude(rs.getString("longitude"));
                airport.setAltitude(rs.getString("altitude"));
                airport.setTimezone(rs.getString("timezone"));
                airport.setDST(rs.getString("DST"));
                airport.setDatabase_timezone(rs.getString("database_timezone"));
                list.add(airport);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("AirportDao: getAllEntity");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addEntity(Entity entity) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO airport (name,city,country,IATA_FAA,latitude,longitude,altitude,timezone,DST,database_timezon) VALUE (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Airport airport = (Airport) entity;
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getCity());
            preparedStatement.setString(3, airport.getCountry());
            preparedStatement.setString(4, airport.getIATA_FAA());
            preparedStatement.setString(5, airport.getLatitude());
            preparedStatement.setString(6, airport.getLongitude());
            preparedStatement.setString(7, airport.getAltitude());
            preparedStatement.setString(8, airport.getTimezone());
            preparedStatement.setString(9, airport.getDST());
            preparedStatement.setString(10, airport.getDatabase_timezone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delEntity(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM airport WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int col = preparedStatement.executeUpdate();
            if (col > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateEntity(Entity entity) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE airport SET name=?,city=?,country=?,IATA_FAA=?,latitude=?,longitude=?,altitude=?,timezone=?,DST=?,database_timezon=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Airport airport = (Airport) entity;
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getCity());
            preparedStatement.setString(3, airport.getCountry());
            preparedStatement.setString(4, airport.getIATA_FAA());
            preparedStatement.setString(5, airport.getLatitude());
            preparedStatement.setString(6, airport.getLongitude());
            preparedStatement.setString(7, airport.getAltitude());
            preparedStatement.setString(8, airport.getTimezone());
            preparedStatement.setString(9, airport.getDST());
            preparedStatement.setString(10, airport.getDatabase_timezone());
            preparedStatement.setInt(11, airport.getId());
            int col = preparedStatement.executeUpdate();
            if (col > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
