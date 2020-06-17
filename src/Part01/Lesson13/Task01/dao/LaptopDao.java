package Part01.Lesson13.Task01.dao;

import Part01.Lesson13.Task01.product.Laptop;

import java.sql.SQLException;

/**
 * LaptopDao.
 *
 * @author Roman Khokhlov
 */
public interface LaptopDao {
    Long addLaptop(Laptop laptop);

    Laptop getLaptopById(Long id);

    boolean updateLaptopById(Laptop laptop);

    boolean deleteLaptopById(Long id);

    void renewDatabase() throws SQLException;
}
