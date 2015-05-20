package model.dao;

import model.entity.Schedule;
import util.DB.DBConnection;
import util.common.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdd on 13/05/15.
 */
public class ScheduleDao {
    
    public static Schedule getSchedule(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM schedule WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Schedule schedule = new Schedule();
            if (rs.next()) {
                schedule.setId(rs.getInt("id"));
                schedule.setFlightID(rs.getString("Flight_ID"));
                schedule.setPlane(rs.getInt("Plane"));
                schedule.setRoute(rs.getInt("Route"));
                String dt = rs.getString("Depart_Time");
                String at = rs.getString("Arrive_Time");
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
                schedule.setDepartTime(sdf.parse(dt));
                schedule.setArrivedTime(sdf.parse(at));
                return schedule;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new DataNotFoundException("ScheduleDao: getSchedule");
    }


    public static List<Schedule> getScheduleInRoute(int routeId) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM schedule WHERE Route=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, routeId);
            ResultSet rs = preparedStatement.executeQuery();
            List<Schedule> list = new ArrayList<Schedule>();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setFlightID(rs.getString("Flight_ID"));
                schedule.setPlane(rs.getInt("Plane"));
                schedule.setRoute(rs.getInt("Route"));
                String dt = rs.getString("Depart_Time");
                String at = rs.getString("Arrive_Time");
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
                schedule.setDepartTime(sdf.parse(dt));
                schedule.setArrivedTime(sdf.parse(at));
                list.add(schedule);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new DataNotFoundException("ScheduleDao: getScheduleInRoute");


    }

    
    public static List<Schedule> getAllSchedule() throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM schedule";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Schedule> list = new ArrayList<Schedule>();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt("id"));
                schedule.setFlightID(rs.getString("Flight_ID"));
                schedule.setPlane(rs.getInt("Plane"));
                schedule.setRoute(rs.getInt("Route"));
                String dt = rs.getString("Depart_Time");
                String at = rs.getString("Arrive_Time");
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
                schedule.setDepartTime(sdf.parse(dt));
                schedule.setArrivedTime(sdf.parse(at));
                list.add(schedule);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("ScheduleDao: getAllSchedule");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    
    public static void addSchedule(Schedule schedule) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO schedule (Flight_ID,Plane,Route,Depart_Time,Arrive_Time) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, schedule.getFlightID());
            preparedStatement.setInt(2, schedule.getPlane());
            preparedStatement.setInt(3, schedule.getRoute());
            preparedStatement.setString(4, schedule.getDepartTime().toString());
            preparedStatement.setString(5, schedule.getArrivedTime().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static boolean delSchedule(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM schedule WHERE id=?";
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

    
    public static boolean updateSchedule(Schedule schedule) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE schedule SET Flight_ID=?,Plane=?,Route=?,Depart_Time=?,Arrive_Time=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, schedule.getFlightID());
            preparedStatement.setInt(2, schedule.getPlane());
            preparedStatement.setInt(3, schedule.getRoute());
            preparedStatement.setString(4, schedule.getDepartTime().toString());
            preparedStatement.setString(5, schedule.getArrivedTime().toString());
            preparedStatement.setInt(6, schedule.getId());
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
