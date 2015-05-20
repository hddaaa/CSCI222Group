package model.dao;

import com.google.gson.Gson;
import model.entity.SeatMap;
import util.DB.DBConnection;
import util.common.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdd on 14/05/15.
 */
public class SeatMapDao {
    
    public static SeatMap getSeatMap(int scheduleId) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM seatmap WHERE scheduleId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, scheduleId);
            ResultSet rs = preparedStatement.executeQuery();
            SeatMap seatMap = new SeatMap();
            if (rs.next()) {
                seatMap.setId(rs.getInt("id"));
                seatMap.setScheduleId(rs.getInt("scheduleId"));
                String m = rs.getString("map");
                Gson gson = new Gson();
                List ml = gson.fromJson(m, ArrayList.class);
                seatMap.setMap(ml);
                seatMap.setfClassSpare(rs.getInt("fClass"));
                seatMap.setbClassSpare(rs.getInt("bClass"));
                seatMap.setPeClassSpare(rs.getInt("peClass"));
                seatMap.seteClassSpare(rs.getInt("eClass"));
                return seatMap;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("SeatMapDao: getSeatMap");
    }

    
    public static List<SeatMap> getAllSeatMap() throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM seatmap";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<SeatMap> list = new ArrayList<SeatMap>();
            while (rs.next()) {
                SeatMap seatMap = new SeatMap();
                seatMap.setId(rs.getInt("id"));
                seatMap.setScheduleId(rs.getInt("scheduleId"));
                String m = rs.getString("map");
                Gson gson = new Gson();
                List ml = gson.fromJson(m, ArrayList.class);
                seatMap.setMap(ml);

                seatMap.setfClassSpare(rs.getInt("fClass"));
                seatMap.setbClassSpare(rs.getInt("bClass"));
                seatMap.setPeClassSpare(rs.getInt("peClass"));
                seatMap.seteClassSpare(rs.getInt("eClass"));
                list.add(seatMap);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("SeatMapDao: getAllSeatMap");

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public static void addSeatMap(SeatMap seatMap) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO seatmap (scheduleId,map,fClass,bClass,peClass,eClass) VALUE (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Gson gson = new Gson();
            preparedStatement.setInt(1, seatMap.getScheduleId());
            preparedStatement.setString(2, gson.toJson(seatMap.getMap()));
            preparedStatement.setInt(3, seatMap.getfClassSpare());
            preparedStatement.setInt(4,seatMap.getbClassSpare());
            preparedStatement.setInt(5,seatMap.getPeClassSpare());
            preparedStatement.setInt(6,seatMap.geteClassSpare());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static boolean delSeatMap(int scheduleId) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM seatmap WHERE scheduleId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, scheduleId);
            int col = preparedStatement.executeUpdate();
            if (col > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static boolean updateSeatMap(SeatMap seatMap) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE seatmap SET map=?,fClass=?,bClass=?,peClass=?,eClass=? WHERE scheduleId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Gson gson = new Gson();
            preparedStatement.setString(1, gson.toJson(seatMap.getMap()));
            preparedStatement.setInt(2, seatMap.getfClassSpare());
            preparedStatement.setInt(3,seatMap.getbClassSpare());
            preparedStatement.setInt(4,seatMap.getPeClassSpare());
            preparedStatement.setInt(5,seatMap.geteClassSpare());
            preparedStatement.setInt(6, seatMap.getScheduleId());
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

