package model.dao;

import model.entity.Ticket;
import model.entity.User;
import sun.security.provider.MD5;
import util.DB.DBConnection;
import util.Enum.UserAuthority;
import util.common.DataNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hdd on 15/05/15.
 */
public class UserDao {
    public static boolean register(User user, String pwd) {
        Connection conn = DBConnection.getConn();
        try {
            String sql = "INSERT INTO user (username,pwd,authority,availability) VALUE (?,MD5(?),?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, pwd);
            preparedStatement.setString(3, user.getAuthority().toString());
            preparedStatement.setBoolean(4, user.isAvailability());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static User login(String username, String pwd){
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM user WHERE username=? AND pwd=MD5(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pwd);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setUsername(username);
                user.setId(rs.getInt("id"));
                user.setAuthority(UserAuthority.valueOf(rs.getString("authority")));
                user.setAvailability(rs.getBoolean("availability"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean updateUser(User user,String oldpwd,String newUsername,String newpwd){
        Connection conn = DBConnection.getConn();
        try {
            String sql = "SELECT * FROM user WHERE username=? AND pwd=MD5(?) AND id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, oldpwd);
            preparedStatement.setInt(3,user.getId());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                sql = "UPDATE user SET username=?,pwd=MD5(?) WHERE id=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, newpwd);
                preparedStatement.setInt(3,user.getId());
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUser(User user){
        Connection conn = DBConnection.getConn();
        try {
            String sql = "UPDATE user SET username=?,authority=?,availability=? WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getAuthority().toString());
            preparedStatement.setBoolean(3, user.isAvailability());
            preparedStatement.setInt(4,user.getId());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
