package model.dao;

import model.entity.Customer;
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
 * Created by hdd on 13/05/15.
 */
public class CustomerDao implements DaoInterface {
    @Override
    public Entity getEntity(int id) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM customer WHERE id=? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Customer customer = new Customer();
            if (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setTitle(rs.getString("title"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setGender(rs.getString("gender"));
                customer.setDOB(rs.getDate("DOB"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("email"));
                customer.setStreet(rs.getString("street_address"));
                customer.setState(rs.getString("state"));
                customer.setCity(rs.getString("city"));
                customer.setCountry(rs.getString("country"));
                customer.setCreditCard(rs.getString("credit_card_type"));
                customer.setCardNum(rs.getString("credit_card_#"));
                customer.setFreqFlierPoint(rs.getInt("frequent_flier_points_"));
                customer.setPassportHolder(rs.getBoolean("passport_holder"));
                customer.setIsFly(rs.getString("if_fly"));
                customer.setTravelAgent(rs.getString("Travel_Agent"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        throw new DataNotFoundException("CustomerDao: getEntity");
    }

    public Customer getCustomerByEmail(String email) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM customer WHERE email=? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            Customer customer = new Customer();
            if (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setTitle(rs.getString("title"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setGender(rs.getString("gender"));
                customer.setDOB(rs.getDate("DOB"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("email"));
                customer.setStreet(rs.getString("street_address"));
                customer.setState(rs.getString("state"));
                customer.setCity(rs.getString("city"));
                customer.setCountry(rs.getString("country"));
                customer.setCreditCard(rs.getString("credit_card_type"));
                customer.setCardNum(rs.getString("credit_card_#"));
                customer.setFreqFlierPoint(rs.getInt("frequent_flier_points_"));
                customer.setPassportHolder(rs.getBoolean("passport_holder"));
                customer.setIsFly(rs.getString("if_fly"));
                customer.setTravelAgent(rs.getString("Travel_Agent"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        throw new DataNotFoundException("CustomerDao: getEntity");
    }

    @Override
    public List<Entity> getAllEntity() throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM customer";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Entity> list = new ArrayList<Entity>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setTitle(rs.getString("title"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setGender(rs.getString("gender"));
                customer.setDOB(rs.getDate("DOB"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("email"));
                customer.setStreet(rs.getString("street_address"));
                customer.setState(rs.getString("state"));
                customer.setCity(rs.getString("city"));
                customer.setCountry(rs.getString("country"));
                customer.setCreditCard(rs.getString("credit_card_type"));
                customer.setCardNum(rs.getString("credit_card_#"));
                customer.setFreqFlierPoint(rs.getInt("frequent_flier_points_"));
                customer.setPassportHolder(rs.getBoolean("passport_holder"));
                customer.setIsFly(rs.getString("if_fly"));
                customer.setTravelAgent(rs.getString("Travel_Agent"));
                list.add(customer);
            }
            if (list.isEmpty()) {
                throw new DataNotFoundException("CustomerDao: getAllEntity");
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Customer> getCustomersByAgent(String agentName) throws DataNotFoundException {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM customer WHERE Travel_Agent=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, agentName);
            ResultSet rs = preparedStatement.executeQuery();
            List<Customer> list = new ArrayList<Customer>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setTitle(rs.getString("title"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setGender(rs.getString("gender"));
                customer.setDOB(rs.getDate("DOB"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("email"));
                customer.setStreet(rs.getString("street_address"));
                customer.setState(rs.getString("state"));
                customer.setCity(rs.getString("city"));
                customer.setCountry(rs.getString("country"));
                customer.setCreditCard(rs.getString("credit_card_type"));
                customer.setCardNum(rs.getString("credit_card_#"));
                customer.setFreqFlierPoint(rs.getInt("frequent_flier_points_"));
                customer.setPassportHolder(rs.getBoolean("passport_holder"));
                customer.setIsFly(rs.getString("if_fly"));
                customer.setTravelAgent(rs.getString("Travel_Agent"));
                list.add(customer);
            }
            if (list.isEmpty()) {
                throw new DataNotFoundException("CustomerDao: getAllEntity");
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addEntity(Entity entity) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO customer (title,first_name,last_name,gender,DOB,Phone,email,street_address,state,city,country,credit_card_type,credit_card_#,frequent_flier_points_,passport_holder,is_fly,Travel_Agent) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Customer customer = (Customer) entity;
            preparedStatement.setString(1, customer.getTitle());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getGender());
            preparedStatement.setDate(5, customer.getDOB());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setString(8, customer.getStreet());
            preparedStatement.setString(9, customer.getState());
            preparedStatement.setString(10, customer.getCity());
            preparedStatement.setString(11, customer.getCountry());
            preparedStatement.setString(12, customer.getCreditCard());
            preparedStatement.setString(13, customer.getCardNum());
            preparedStatement.setInt(14, customer.getFreqFlierPoint());
            preparedStatement.setBoolean(15, customer.isPassportHolder());
            preparedStatement.setString(16, customer.getIsFly());
            preparedStatement.setString(17, customer.getTravelAgent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delEntity(int id) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "DELETE FROM customer WHERE id=?";
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
            String sql = "UPDATE customer SET title=?,first_name=?,last_name=?,gender=?,DOB=?,Phone=?,email=?,street_address=?,state=?,city=?,country=?,credit_card_type=?,credit_card_#=?,frequent_flier_points_=?,passport_holder=?,is_fly=?,Travel_Agent=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            Customer customer = (Customer) entity;
            preparedStatement.setString(1, customer.getTitle());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getGender());
            preparedStatement.setDate(5, customer.getDOB());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());
            preparedStatement.setString(8, customer.getStreet());
            preparedStatement.setString(9, customer.getState());
            preparedStatement.setString(10, customer.getCity());
            preparedStatement.setString(11, customer.getCountry());
            preparedStatement.setString(12, customer.getCreditCard());
            preparedStatement.setString(13, customer.getCardNum());
            preparedStatement.setInt(14, customer.getFreqFlierPoint());
            preparedStatement.setBoolean(15, customer.isPassportHolder());
            preparedStatement.setString(16, customer.getIsFly());
            preparedStatement.setString(17, customer.getTravelAgent());
            preparedStatement.setInt(18, customer.getId());
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
