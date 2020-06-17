package Part01.Lesson13.Task01.Test.dao;

import Part01.Lesson13.Task01.ConnectionManager.ConnectionManager;
import Part01.Lesson13.Task01.ConnectionManager.ConnectionManagerJdbcImpl;
import Part01.Lesson13.Task01.Test.TestResultLoggerExtension;
import Part01.Lesson13.Task01.dao.LaptopDao;
import Part01.Lesson13.Task01.dao.LaptopDaoJdbcImpl;
import Part01.Lesson13.Task01.product.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * MobileDaoJdbcImplTest.
 *
 * @author Roman Khokhlov
 */
@ExtendWith(TestResultLoggerExtension.class)
class LaptopDaoJdbcImplTest {

    private LaptopDao laptopDao;
    private ConnectionManager connectionManager;
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSetMock;

    @BeforeEach
    void setUp() throws SQLException {
        initMocks(this);
        connectionManager = spy(ConnectionManagerJdbcImpl.getInstance());
        connection = mock(Connection.class);
        laptopDao = spy(new LaptopDaoJdbcImpl(connectionManager));
    }

    @Test
    void addLaptop() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatement).when(connection).prepareStatement(LaptopDaoJdbcImpl.INSERT_INTO_PRODUCTS, Statement.RETURN_GENERATED_KEYS);
        doReturn(resultSetMock).when(preparedStatement).getGeneratedKeys();
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getLong(1)).thenReturn(1L);
        int id = 1;
        String name = "APPLE";
        String productGroup = "PC";
        double price = 100000.00;
        Laptop laptop = new Laptop(id, name, productGroup, price);

        Long result = laptopDao.addLaptop(laptop);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, atMost(1)).prepareStatement(LaptopDaoJdbcImpl.INSERT_INTO_PRODUCTS);
        verify(preparedStatement, times(1)).setString(1, name);
        verify(preparedStatement, times(1)).setString(2, productGroup);
        verify(preparedStatement, times(1)).setDouble(3, price);
        verify(preparedStatement, times(1)).executeUpdate();
        assertAll("assert all",
                () -> assertEquals(1L, result),
                () -> assertNotEquals(1L, result)
        );
    }

    @Test
    void addLaptopWithSqlException() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatement).when(connection).prepareStatement(LaptopDaoJdbcImpl.INSERT_INTO_PRODUCTS, Statement.RETURN_GENERATED_KEYS);
        doThrow(new SQLException("HELLO!")).when(preparedStatement).executeUpdate();
        int id = 1;
        String name = "APPLE";
        String productGroup = "PC";
        double price = 100000.00;
        Laptop laptop = new Laptop(id, name, productGroup, price);

        Long result = assertDoesNotThrow(() -> laptopDao.addLaptop(laptop));

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(LaptopDaoJdbcImpl.INSERT_INTO_PRODUCTS, Statement.RETURN_GENERATED_KEYS);
        verify(preparedStatement, atMost(2)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).setDouble(3, price);
        verify(preparedStatement, never()).executeQuery();
        verify(preparedStatement, times(1)).executeUpdate();
        assertEquals(0L, result);
    }

    @Test
    void test1() {
        int result = connectionManager.get15();
        assertEquals(15, result);
    }

    @Test
    void test2() {
        when(connectionManager.get15()).thenAnswer(invocationOnMock -> ((int) invocationOnMock.callRealMethod()) + 5);

        int result = connectionManager.get15();

        assertEquals(20, result);
    }

}
