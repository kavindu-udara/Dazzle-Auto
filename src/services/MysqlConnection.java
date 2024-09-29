package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConnection {

    private static Connection connection;

    public static void createConnection() throws Exception {

        final String DB_HOST = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "service_station_db";
        final String DB_USER = "root";
        final String DB_PASSWORD = "";

        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "", DB_USER, DB_PASSWORD);
        }
    }
    
    public static ResultSet executeSearch(String query) throws Exception {
        createConnection();
        return connection.createStatement().executeQuery(query);
    }
    
    public static ResultSet executeIUD(String query) throws Exception {
        
        createConnection();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer generatedId = null;

        // Prepare the statement with RETURN_GENERATED_KEYS
        pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
        // Execute the update (for INSERT/UPDATE/DELETE)
        int affectedRows = pstmt.executeUpdate();

        if (affectedRows > 0) {
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); 
            }
        }
        return pstmt.getGeneratedKeys();
    }
}
