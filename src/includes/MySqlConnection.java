package includes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Logger;
import views.database.CreateConnectionDialog;

public class MySqlConnection implements Serializable {

    private static final Logger logger = LoggerConfig.getLogger();

    public static Connection connection;

    public String HOST;
    public String PORT;
    public String PASSWORD;
    public String USERNAME;
    public String DBNAME;

    // DATA BACKUP VARIABLES - not created
    public String DUMP;
    public String PATH;

    public static void createConnection(MySqlConnection mySqlConnection) throws Exception {
        if (connection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + mySqlConnection.HOST + ":" + mySqlConnection.PORT + "/" + mySqlConnection.DBNAME + "", mySqlConnection.USERNAME, mySqlConnection.PASSWORD);
        }
    }

    public static void setupConnection() {
        try (FileInputStream inputStream = new FileInputStream("dbinfo.ser")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            MySqlConnection mySqlConnection = (MySqlConnection) objectInputStream.readObject();
            objectInputStream.close();
            createConnection(mySqlConnection);
        } catch (FileNotFoundException e) {
            new CreateConnectionDialog(null, true).setVisible(true);
            logger.severe("File Failed : " + e.getMessage());
        } catch (IOException e) {
            new CreateConnectionDialog(null, true).setVisible(true);
            logger.severe("File Failed : " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            new CreateConnectionDialog(null, true).setVisible(true);
            logger.severe("MYSQL Connection Failed : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.severe("Class Not Found : " + e.getMessage());
        } catch (Exception ex) {
            logger.severe("Error while setup connection : " + ex.getMessage());
        }
    }

    public static void testConnection(String DB_URL, String DB_USER, String DB_PASSWORD, Runnable successCallBack, Runnable faildCallBack) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if (connection != null) {
                logger.info("Test Connection Success : " + "DB_URL=" + DB_URL + " DB_USER=" + DB_USER);
                successCallBack.run();
            } else {
                System.out.println("Failed to make connection!");
                logger.warning("Test Connection Failed : " + "DB_URL=" + DB_URL + " DB_USER=" + DB_USER);
                faildCallBack.run();
            }
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            logger.warning("Test Connection Failed : " + "DB_URL=" + DB_URL + " DB_USER=" + DB_USER + " Exception : " + e.getMessage());
            e.printStackTrace();
            faildCallBack.run();
        }
    }

    public static ResultSet executeSearch(String query) throws Exception {
        setupConnection();
        return connection.createStatement().executeQuery(query);
    }

    public static ResultSet executeIUD(String query) throws Exception {

        setupConnection();
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
