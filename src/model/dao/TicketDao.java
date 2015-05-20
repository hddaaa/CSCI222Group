package model.dao;

import model.entity.Ticket;
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
public class TicketDao {

    public static Ticket getTicket(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM ticket WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Ticket ticket = new Ticket();
            if (rs.next()) {
                ticket.setId(rs.getInt("id"));
                ticket.setCustomerId(rs.getInt("customerId"));
                ticket.setUsername(rs.getString("username"));
                ticket.setScheduleId(rs.getInt("scheduleId"));
                ticket.setFareClass(rs.getString("fareClass"));
                ticket.setSeat(rs.getInt("seat"));
                ticket.setFlightCost(rs.getInt("flightCost"));
                ticket.setServiceCost(rs.getInt("serviceCost"));
                ticket.setTotal(rs.getInt("total"));
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("TicketDao: getTicket");
    }

    public static Ticket getTicket(int customerId, int scheduleId) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM ticket WHERE customerId=? AND scheduleId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, scheduleId);
            ResultSet rs = preparedStatement.executeQuery();
            Ticket ticket = new Ticket();
            if (rs.next()) {
                ticket.setId(rs.getInt("id"));
                ticket.setCustomerId(rs.getInt("customerId"));
                ticket.setUsername(rs.getString("username"));
                ticket.setScheduleId(rs.getInt("scheduleId"));
                ticket.setFareClass(rs.getString("fareClass"));
                ticket.setSeat(rs.getInt("seat"));
                ticket.setFlightCost(rs.getInt("flightCost"));
                ticket.setServiceCost(rs.getInt("serviceCost"));
                ticket.setTotal(rs.getInt("total"));
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        throw new DataNotFoundException("TicketDao: getTicket");
    }


    public static List<Ticket> getAllTicket() {
        return null;
    }

    public static List<Ticket> getAllTicketFormACustomer(int customerId) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM ticket WHERE customerId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();
            List<Ticket> list = new ArrayList<Ticket>();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setCustomerId(rs.getInt("customerId"));
                ticket.setUsername(rs.getString("username"));
                ticket.setScheduleId(rs.getInt("scheduleId"));
                ticket.setFareClass(rs.getString("fareClass"));
                ticket.setSeat(rs.getInt("seat"));
                ticket.setFlightCost(rs.getInt("flightCost"));
                ticket.setServiceCost(rs.getInt("serviceCost"));
                ticket.setTotal(rs.getInt("total"));
                list.add(ticket);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("TicketDao: getAllTicketFormACustomer");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Ticket> getAllTicketFormAgent(int agentId) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM ticket WHERE userId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, agentId);
            ResultSet rs = preparedStatement.executeQuery();
            List<Ticket> list = new ArrayList<Ticket>();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setCustomerId(rs.getInt("customerId"));
                ticket.setUsername(rs.getString("username"));
                ticket.setScheduleId(rs.getInt("scheduleId"));
                ticket.setFareClass(rs.getString("fareClass"));
                ticket.setSeat(rs.getInt("seat"));
                ticket.setFlightCost(rs.getInt("flightCost"));
                ticket.setServiceCost(rs.getInt("serviceCost"));
                ticket.setTotal(rs.getInt("total"));
                list.add(ticket);
            }
            if (list.isEmpty())
                throw new DataNotFoundException("TicketDao: getAllTicketFormACustomer");
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void addTicket(Ticket ticket) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO ticket (customerId,username,scheduleId,fareClass,seat,flightCost,serviceCost,total) VALUE (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getCustomerId());
            preparedStatement.setString(2, ticket.getUsername());
            preparedStatement.setInt(3, ticket.getScheduleId());
            preparedStatement.setString(4, ticket.getFareClass());
            preparedStatement.setInt(5, ticket.getSeat());
            preparedStatement.setInt(6, ticket.getFlightCost());
            preparedStatement.setInt(7, ticket.getServiceCost());
            preparedStatement.setInt(8, ticket.getTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }


    public static boolean delTicket(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM ticket WHERE id=?";
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


    public static boolean updateTicket(Ticket ticket) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE ticket SET customerId=?,username=?,scheduleId=?,fareClass=?,seat=?,flightCost=?,serviceCost=?,total=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ticket.getCustomerId());
            preparedStatement.setString(2, ticket.getUsername());
            preparedStatement.setInt(3, ticket.getScheduleId());
            preparedStatement.setString(4, ticket.getFareClass());
            preparedStatement.setInt(5, ticket.getSeat());
            preparedStatement.setInt(6, ticket.getFlightCost());
            preparedStatement.setInt(7, ticket.getServiceCost());
            preparedStatement.setInt(8, ticket.getTotal());
            preparedStatement.setInt(9, ticket.getId());
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
