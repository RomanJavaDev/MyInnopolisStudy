package Part01.Lesson13.Task01.ConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionManagerJdbcImpl.
 *
 * @author Roman Khokhlov
 */
public class ConnectionManagerJdbcImpl implements ConnectionManager {
    public static final ConnectionManager INSTANCE = new ConnectionManagerJdbcImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManagerJdbcImpl.class);

    private ConnectionManagerJdbcImpl() {
    }

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/my_online_shop?serverTimezone=UTC&useSSL=false",
                    "root",
                    "roma");
        } catch (SQLException e) {
            LOGGER.error("Something wrong in getConnection method", e);
        }
        return connection;
    }

    @Override
    public int get15() {
        return 15;
    }

}
