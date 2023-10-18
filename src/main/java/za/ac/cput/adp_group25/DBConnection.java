/*
Coded By: Imaan Sadien 221752838
*/
package za.ac.cput.adp_group25;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
public static Connection getConnection() throws SQLException {
        String DATABASE_URL = "jdbc:derby://localhost:1527/StudentEnrolmentDB";
        String username = "administrator";
        String password = "password";
        
        Connection connection = DriverManager.getConnection(DATABASE_URL, username, password);
        return connection;
    } 
}
