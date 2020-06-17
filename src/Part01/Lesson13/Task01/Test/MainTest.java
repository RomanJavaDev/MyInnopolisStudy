package Part01.Lesson13.Task01.Test;

import Part01.Lesson13.Task01.ConnectionManager.ConnectionManager;
import Part01.Lesson13.Task01.Main;
import Part01.Lesson13.Task01.Test.mocks.ConnectionManagerMock;
import Part01.Lesson13.Task01.dao.LaptopDao;
import Part01.Lesson13.Task01.dao.LaptopDaoJdbcImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * MainTest.
 *
 * @author Roman Khokhlov
 */

class MainTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);

    private Main main;
    private LaptopDao laptopDao;
    private ConnectionManager connectionManager;

    @BeforeAll
    static void tearDownAll() {
        LOGGER.trace("BeforeAll in MainTest");
    }

    @AfterAll
    static void setUpAll() {
        LOGGER.trace("AfterAll in MainTest");
    }

    private static Stream<Arguments> getPriceTestArgumentProvider() {
        return Stream.of(
                Arguments.of(1, 1, 1),
                Arguments.of(2, 3, 6)
        );
    }

    @BeforeEach
    void setUp() throws SQLException {
        LOGGER.trace("BeforeEach in MainTest");
        main = new Main();
        connectionManager = (ConnectionManager) new ConnectionManagerMock();
        laptopDao = new LaptopDaoJdbcImpl(connectionManager);
    }

    @AfterEach
    void tearDown() {
        LOGGER.trace("AfterEach in MainTest");
    }

    @Test
    @DisplayName("ТЕСТ MAIN МЕТОДА, КОГДА ВСЁ ОК!")
    void main() {
        assumeTrue(main != null);
        assertDoesNotThrow(() -> main.method1(laptopDao));
    }

    @Test
    @Disabled
    void disabledTest() {
        //
    }

    @Test
    void mainWithException() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> main.method1(null));
        assertNull(nullPointerException.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 1",
            "2, 3, 6"
    })
    void getPriceTestWithValueSource(double oldPrice, int multiply, double result) {
        double price = main.getPrice(oldPrice, multiply);
        assertEquals(result, price);
    }

    @ParameterizedTest(name = "{index} / {0} * {1} = {2}")
    @MethodSource("getPriceTestArgumentProvider")
    void getPriceTestWithMethodSource(ArgumentsAccessor argumentsAccessor) {
        int oldPrice = argumentsAccessor.getInteger(0);
        int multiply = argumentsAccessor.get(1, Integer.class);
        int result = (int) argumentsAccessor.get(2);
        double price = main.getPrice(oldPrice, multiply);
        assertEquals(result, price);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C"})
        //@CsvSource({
        //            "1, 1, 1",
        //            "2, 3, 6"
        //    })
        //@CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void parametrizedTest(ArgumentsAccessor argumentsAccessor) {
        String s1 = argumentsAccessor.getString(0);
        assertEquals("A", s1);
    }

}
