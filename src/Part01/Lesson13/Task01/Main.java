package Part01.Lesson13.Task01;

import Part01.Lesson13.Task01.ConnectionManager.ConnectionManager;
import Part01.Lesson13.Task01.ConnectionManager.ConnectionManagerJdbcImpl;
import Part01.Lesson13.Task01.dao.LaptopDao;
import Part01.Lesson13.Task01.dao.LaptopDaoJdbcImpl;
import Part01.Lesson13.Task01.product.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Main.
 *
 * @author Roman Khokhlov
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
        LaptopDao laptopDao = new LaptopDaoJdbcImpl(connectionManager);
        laptopDao.renewDatabase();
        Main main = new Main();
        main.method1(laptopDao);
    }

    public void method1(LaptopDao laptopDao) {
        Laptop laptop = new Laptop(null, "HP", "Personal Computer", 35000.00);
        Long aLong = laptopDao.addLaptop(laptop);
        laptop = laptopDao.getLaptopById(aLong);
        LOGGER.info("Начальный объект: {}", laptop);
        laptop.setPrice(getPrice(laptop.getPrice(), 2));
        laptopDao.updateLaptopById(laptop);
        laptop = laptopDao.getLaptopById(aLong);
        LOGGER.info("Итоговый объект: {}", laptop);
    }

    public double getPrice(double oldPrice, int multiply) {
        return (double) oldPrice * multiply;
    }

}
