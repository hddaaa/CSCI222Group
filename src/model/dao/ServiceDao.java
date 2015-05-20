package model.dao;

import model.entity.Service;
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
public class ServiceDao {
    public static Service getService(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM sevice WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Service service = new Service();
            if (rs.next()) {
                service.setId(rs.getInt("id"));
                service.setCustomerId(rs.getInt("customerId"));
                service.setTicketId(rs.getInt("ticketId"));
                service.setItem(rs.getString("item"));
                service.setCost(rs.getInt("cost"));
                return service;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("ServiceDao: getService");
    }

    
    public static List<Service> getAllService() {
        return null;
    }

    public static List<Service> getServicesInATicket(int ticketId) throws DataNotFoundException {

        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM service WHERE ticketId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ticketId);
            ResultSet rs = preparedStatement.executeQuery();
            List<Service> list = new ArrayList<Service>();
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setCustomerId(rs.getInt("customerId"));
                service.setTicketId(rs.getInt("ticketId"));
                service.setItem(rs.getString("item"));
                service.setCost(rs.getInt("cost"));
                list.add(service);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("ServiceDao: getServicesInATicket");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Service> getServicesInACustomer(int customerId) throws DataNotFoundException {

        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM service WHERE customerId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();
            List<Service> list = new ArrayList<Service>();
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setCustomerId(rs.getInt("customerId"));
                service.setTicketId(rs.getInt("ticketId"));
                service.setItem(rs.getString("item"));
                service.setCost(rs.getInt("cost"));
                list.add(service);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("ServiceDao: getServicesInACustomer");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    
    public static void addService(Service service) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO service (ticketId,customerId,item,cost) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, service.getTicketId());
            preparedStatement.setInt(2, service.getCustomerId());
            preparedStatement.setString(3, service.getItem());
            preparedStatement.setInt(4, service.getCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static boolean delService(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM service WHERE id=?";
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

    
    public static boolean updateService(Service service) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE service SET ticketId=?,customerId=?,item=?,cost=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, service.getTicketId());
            preparedStatement.setInt(2, service.getCustomerId());
            preparedStatement.setString(3, service.getItem());
            preparedStatement.setInt(4, service.getCost());
            preparedStatement.setInt(5, service.getId());
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
