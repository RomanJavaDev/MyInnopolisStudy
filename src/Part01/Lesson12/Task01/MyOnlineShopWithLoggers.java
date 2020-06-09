package Part01.Lesson12.Task01;

import java.sql.*;
import java.util.logging.*;

/**
 * MyOnlineShopWithLoggers.
 * <p>
 * Задание 1. Взять за основу предыдущее домашнее задание:
 * Заменить весь вывод System.out.println на логгирование любым выбранным фреймворком.
 * Продумать схему использования уровней логгирования (Напр. уровень DEBUG для событий используемых в отладке приложения и не отображаемых при запуске приложения в штатном режиме).
 * <p>
 * Задание 2. Настроить логгирование:
 * Разделить условно логи на системные, логи безопасности, логи бизнес событий.
 * Настроить формат для логов каждого из видов.
 * Продумать удобный вид хранения для логов каждого из видов.
 * Использовать запись в заранее заготовленную таблицу в базе данных.
 * Использовать запись в файл с ограничением по размеру файла, сроку хранения файла и ротацией файлов.
 *
 * @author Roman Khokhlov
 */
public class MyOnlineShopWithLoggers {

    private static final Logger log = Logger.getLogger(Part01.Lesson11.Task01.MyOnlineShop.class.getName());

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new MyFormatter());
        consoleHandler.setFilter(new MyFilter());
        log.setUseParentHandlers(false);
        log.addHandler(consoleHandler);
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
            log.info("Using PreparedStatement:");
            // Применяем параметризованный запрос:
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE subgroup = ? AND price < ?")) {
                preparedStatement.setString(1, "Laptops");
                preparedStatement.setDouble(2, 60000.00);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        log.info("id: " + resultSet.getInt("id"));
                        log.info("name: " + resultSet.getString("name"));
                        log.info("productGroup: " + resultSet.getString("productGroup"));
                        log.info("subgroup: " + resultSet.getString("subgroup"));
                        log.info("description: " + resultSet.getString("description"));
                        log.info("price: " + resultSet.getDouble("price"));
                        System.out.println();
                    }
                }
            }
            log.info("Using Batching:");
            // Применяем батчинг:
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET price = 40000 WHERE id = ?")) {
                for (Integer arg : localArgs) {
                    preparedStatement.setInt(1, arg);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
            try (Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);  // ручное управление коммитами
                statement.executeUpdate("INSERT INTO products VALUES (NULL, 'Samsung', 'PC', 'Laptops', 'Intel Core i5, DDR4 8GB', 50000)");
                Savepoint savepoint = connection.setSavepoint(); // использование savepoint при выполнении логики из нескольких запросов
                statement.executeUpdate("INSERT INTO products VALUES (NULL, 'HP', 'PC', 'Laptops', 'Intel Core i3, DDR4 6GB', 30000)");
                connection.rollback(savepoint); // использование rollback и savepoint
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                connection.rollback();  // предусмотрен rollback операций при ошибках
                log.warning(e.toString());
            }
        }
    }

    static class MyFormatter extends Formatter {

        @Override
        public String format(LogRecord logRecord) {
            return "\n" + logRecord.getLevel() + " (" + logRecord.getLoggerName() + "): " + logRecord.getMessage() + "\n";
        }
    }

    static class MyFilter implements Filter {

        @Override
        public boolean isLoggable(LogRecord logRecord) {
            return logRecord.getLevel().equals(Level.INFO);
        }
    }
}