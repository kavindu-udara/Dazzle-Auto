package includes;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class MySqlConnection {

    private static Connection connection;
    
    private static Dotenv dotenv = Dotenv.load();

    public static void createConnection() throws Exception {
        
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + dotenv.get("DB_HOST") + ":" + dotenv.get("DB_PORT") + "/" + dotenv.get("DB_NAME") + "", dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
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
