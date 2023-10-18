/*
Coded By: Imaan Sadien 221752838
*/
package za.ac.cput.adp_group25;

import java.sql.*;

public class DBConnection {
    
    public static final String getURL = "jdbc:derby://localhost:1527/StudentEnrolmentDB";
    public static final String administrator = "administrator";
    public static final String password = "password";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(getURL, administrator, password);
    }
    
    
}
