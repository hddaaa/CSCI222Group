package model.dao;

import model.entity.ServiceInventory;
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
public class ServiceInventoryDao{
    
    public static ServiceInventory getServiceInventory(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM serviceInventory WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            ServiceInventory serviceInventory = new ServiceInventory();
            if (rs.next()) {
                serviceInventory.setId(rs.getInt("id"));
                serviceInventory.setItem(rs.getString("Item"));
                serviceInventory.setCost(rs.getInt("Cost_AU"));
                serviceInventory.setAvailability(rs.getString("Availability"));
                return serviceInventory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("ServiceInventoryDao: getServiceInventory");
    }

    
    public static List<ServiceInventory> getAllServiceInventory() throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM serviceInventory";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<ServiceInventory> list = new ArrayList<ServiceInventory>();
            while (rs.next()) {
                ServiceInventory serviceInventory = new ServiceInventory();
                serviceInventory.setId(rs.getInt("id"));
                serviceInventory.setItem(rs.getString("Item"));
                serviceInventory.setCost(rs.getInt("Cost_AU"));
                serviceInventory.setAvailability(rs.getString("Availability"));
                list.add(serviceInventory);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("ServiceInventoryDao: getAllServiceInventory");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ServiceInventory> getServicesByAvailable(String ava) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM serviceInventory WHERE Availability=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,ava);
            ResultSet rs = preparedStatement.executeQuery();
            List<ServiceInventory> list = new ArrayList<ServiceInventory>();
            while (rs.next()) {
                ServiceInventory serviceInventory = new ServiceInventory();
                serviceInventory.setId(rs.getInt("id"));
                serviceInventory.setItem(rs.getString("Item"));
                serviceInventory.setCost(rs.getInt("Cost_AU"));
                serviceInventory.setAvailability(rs.getString("Availability"));
                list.add(serviceInventory);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("ServiceInventoryDao: getAllServiceInventory");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    
    public static void addServiceInventory(ServiceInventory serviceInventory) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO serviceInventory (Item,Cost_AU,Availability) VALUE (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, serviceInventory.getItem());
            preparedStatement.setInt(2, serviceInventory.getCost());
            preparedStatement.setString(3, serviceInventory.getAvailability());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static boolean delServiceInventory(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM serviceInventory WHERE id=?";
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

    
    public static boolean updateServiceInventory(ServiceInventory serviceInventory) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE serviceInventory SET Item=?,Cost_AU=?,Availability=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, serviceInventory.getItem());
            preparedStatement.setInt(2, serviceInventory.getCost());
            preparedStatement.setString(3, serviceInventory.getAvailability());
            preparedStatement.setInt(4, serviceInventory.getId());
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
