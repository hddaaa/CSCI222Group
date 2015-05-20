package model.dao;

import model.entity.Fleet;
import util.DB.DBConnection;
import util.common.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hdd on 13/05/15.
 */
public class FleetDao {
    
    public static Fleet getFleet(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM fleet WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Fleet fleet = new Fleet();
            if (rs.next()) {
                fleet.setId(rs.getInt("id"));
                fleet.setAircraft(rs.getString("Aircraft"));
                fleet.setInService(rs.getInt("In_Service"));
                fleet.setFClass(rs.getInt("FClass"));
                fleet.setBClass(rs.getInt("BClass"));
                fleet.setPEClass(rs.getInt("PEClass"));
                fleet.setEClass(rs.getInt("EClass"));
                fleet.setTotal(rs.getInt("Total"));
                return fleet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("FleetDao: getFleet");
    }

    
    public static List<Fleet> getAllFleet() throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM fleet";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Fleet> list = new ArrayList<Fleet>();
            while (rs.next()) {
                Fleet fleet = new Fleet();
                fleet.setId(rs.getInt("id"));
                fleet.setAircraft(rs.getString("Aircraft"));
                fleet.setInService(rs.getInt("In_Service"));
                fleet.setFClass(rs.getInt("FClass"));
                fleet.setBClass(rs.getInt("BClass"));
                fleet.setPEClass(rs.getInt("PEClass"));
                fleet.setEClass(rs.getInt("EClass"));
                fleet.setTotal(rs.getInt("Total"));
                list.add(fleet);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("FleetDao: getAllFleet");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public static void addFleet(Fleet fleet) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO fleet (Aircraft,In_Service,FClass,BClass,PEClass,EClass,Total) VALUE (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fleet.getAircraft());
            preparedStatement.setInt(2, fleet.getInService());
            preparedStatement.setInt(3, fleet.getFClass());
            preparedStatement.setInt(4, fleet.getBClass());
            preparedStatement.setInt(5, fleet.getPEClass());
            preparedStatement.setInt(6, fleet.getEClass());
            preparedStatement.setInt(7, fleet.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    
    public static boolean delFleet(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM fleet WHERE id=?";
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

    
    public static boolean updateFleet(Fleet fleet) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE fleet SET Aircraft=?,In_Service=?,FClass=?,BClass=?,PEClass=?,EClass=?,Total=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fleet.getAircraft());
            preparedStatement.setInt(2, fleet.getInService());
            preparedStatement.setInt(3, fleet.getFClass());
            preparedStatement.setInt(4, fleet.getBClass());
            preparedStatement.setInt(5, fleet.getPEClass());
            preparedStatement.setInt(6, fleet.getEClass());
            preparedStatement.setInt(7, fleet.getTotal());
            preparedStatement.setInt(8, fleet.getId());
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
