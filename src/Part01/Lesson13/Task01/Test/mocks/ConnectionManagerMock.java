package Part01.Lesson13.Task01.Test.mocks;

import Part01.Lesson13.Task01.ConnectionManager.ConnectionManager;

import java.sql.Connection;

/**
 * ConnectionManagerMock.
 *
 * @author Roman Khokhlov
 */
public class ConnectionManagerMock implements ConnectionManager {
    @Override public Connection getConnection() {
        return new ConnectionMock();
    }

    @Override public int get15() {
        return 0;
    }
}
