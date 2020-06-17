package Part01.Lesson13.Task01.dao;

import Part01.Lesson13.Task01.ConnectionManager.ConnectionManager;
import Part01.Lesson13.Task01.product.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * LaptopDaoJdbcImpl.
 *
 * @author Roman Khokhlov
 */
public class LaptopDaoJdbcImpl implements LaptopDao {
    public static final String INSERT_INTO_PRODUCTS = "INSERT INTO products values (DEFAULT, ?, ?, ?)";
    public static final String SELECT_FROM_PRODUCTS = "SELECT * FROM products WHERE id = ?";
    public static final String UPDATE_PRODUCTS = "UPDATE products SET name=?, productGroup=?, price=? WHERE id=?";
    public static final String DELETE_FROM_PRODUCTS = "DELETE FROM products WHERE id=?";
    private static final Logger LOGGER = LoggerFactory.getLogger(LaptopDaoJdbcImpl.class);
    private ConnectionManager connectionManager;

    public LaptopDaoJdbcImpl(ConnectionManager connectionManager) throws SQLException {
        this.connectionManager = connectionManager;
    }

    @Override
    public Long addLaptop(Laptop laptop) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_PRODUCTS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, laptop.getName());
            preparedStatement.setString(2, laptop.getProductGroup());
            preparedStatement.setDouble(3, laptop.getPrice());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in addLaptop method", e);
        }
        return 0L;
    }

    @Override
    public Laptop getLaptopById(Long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PRODUCTS)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Laptop(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in getLaptopById method", e);
        }
        return null;
    }

    @Override
    public boolean updateLaptopById(Laptop laptop) {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTS)) {
            preparedStatement.setString(1, laptop.getName());
            preparedStatement.setString(2, laptop.getProductGroup());
            preparedStatement.setDouble(3, laptop.getPrice());
            preparedStatement.setInt(4, laptop.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in updateLaptopById method", e);
        }
        return false;
    }

    @Override
    public boolean deleteLaptopById(Long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_PRODUCTS)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in deleteLaptopById method", e);
            return false;
        }
        return true;
    }

    @Override
    public void renewDatabase() throws SQLException {
        String create = "CREATE TABLE products " +
                "(id INT AUTO_INCREMENT, " +
                " name VARCHAR(100), " +
                " productGroup VARCHAR(100), " +
                " price DOUBLE, " +
                " PRIMARY KEY (id));";
        String drop = "DROP TABLE IF EXISTS products;";
        String insert = "INSERT INTO products (name, productGroup, price)\n"
                + "VALUES\n"
                + "   ('Acer Aspire 3', 'Laptops', 40000.00),\n"
                + "   ('Apple MacBook Air 13', 'Laptops', 60000.00),\n"
                + "   ('Asus VivoBook 15', 'Laptops', 35000.00),\n"
                + "   ('Samsung', 'Laptops', 45000.00);";

        try (
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute(drop);
            statement.execute(create);
            statement.execute(insert);
        }
    }

}
