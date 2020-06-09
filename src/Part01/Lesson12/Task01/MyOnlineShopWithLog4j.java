package Part01.Lesson12.Task01;

import org.apache.logging.log4j.*;
import java.sql.*;


/**
 * MyOnlineShopWithLog4j.
 *
 * @author Roman Khokhlov
 */
public class MyOnlineShopWithLog4j {
    private static final Logger log = LogManager.getLogger(MyOnlineShopWithLog4j.class.getName());

    public static void main(String[] args) throws ClassNotFoundException {
        Marker marker = MarkerManager.getMarker("consoleonly");
        log.info(new Throwable("INFO"));
        log.throwing(Level.INFO, new Throwable("INFO"));
        log.info(marker, "Message1");
        log.debug("DEBUG");
        log.trace("TRACE");
        log.fatal("FATAL");

        Integer[] localArgs = new Integer[]{1, 2, 3, 4};
        String userName = "root";
        String password = "roma";
        String connectionURL = "jdbc:mysql://localhost:3306/my_online_shop?serverTimezone=UTC&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password)) {
            log.info("We're connected");
            log.info("Using Statement:");
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM customers")) {
                    while (resultSet.next()) {
                        log.info("id: " + resultSet.getInt("id"));
                        log.info("firstName: " + resultSet.getString("firstName"));
                        log.info("lastName: " + resultSet.getString("lastName"));
                        log.info("address: " + resultSet.getString("address"));
                        log.info("phone: " + resultSet.getString("phone"));
                        log.info("email: " + resultSet.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
