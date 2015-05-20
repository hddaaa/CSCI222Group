package util.DB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by hdd on 13/05/15.
 */
public class DBConnection {
    private static Connection conn;

    public static Connection getConn() {
        if (conn == null) {
            try {
                Context initialContext = new InitialContext();
                DataSource ds = (DataSource) initialContext.lookup("jdbc/DB399DS");
                conn = ds.getConnection();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
