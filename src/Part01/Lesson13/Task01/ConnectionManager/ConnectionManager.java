package Part01.Lesson13.Task01.ConnectionManager;

import java.sql.Connection;

/**
 * ConnectionManager.
 *
 * @author Roman Khokhlov
 */
public interface ConnectionManager {
    Connection getConnection();

    int get15();
}
