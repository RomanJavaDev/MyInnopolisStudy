package Part01.Lesson11.Task01;

import java.sql.*;

/**
 * MyOnlineShop.
 * <p>
 * Задание 1. Взять за основу предметную область выбранную на занятии по UML:
 * Спроектировать базу данных для выбранной предметной области (минимум три таблицы).
 * Типы и состав полей в таблицах на ваше усмотрение.
 * Связи между таблицами делать не обязательно.
 * <p>
 * Задание 2. Через JDBC интерфейс описать CRUD операции с созданными таблицами:
 * Применить параметризованный запрос.
 * Применить батчинг.
 * Использовать ручное управление транзакциями.
 * Предусмотреть использование savepoint при выполнении логики из нескольких запросов.
 * Предусмотреть rollback операций при ошибках.
 * Желательно предусмотреть метод сброса и инициализации базы данных.
 *
 * @author Roman Khokhlov
 */
public class MyOnlineShop {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Integer[] localArgs = new Integer[]{1, 2, 3, 4};
        String userName = "root";
        String password = "roma";
        String connectionURL = "jdbc:mysql://localhost:3306/my_online_shop?serverTimezone=UTC&useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password)) {
            System.out.println("We're connected");
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM customers")) {
                    while (resultSet.next()) {
                        System.out.print("id: " + resultSet.getInt("id"));
                        System.out.print("; firstName: " + resultSet.getString("firstName"));
                        System.out.print("; lastName: " + resultSet.getString("lastName"));
                        System.out.print("; address: " + resultSet.getString("address"));
                        System.out.print("; phone: " + resultSet.getString("phone"));
                        System.out.print("; email: " + resultSet.getString("email"));
                    }
                }
            }
            System.out.println();
            System.out.println("---------------------------");
            // Применяем параметризованный запрос:
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE subgroup = ? AND price < ?")) {
                preparedStatement.setString(1, "Laptops");
                preparedStatement.setDouble(2, 60000.00);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.print("id: " + resultSet.getInt("id"));
                        System.out.print("; name: " + resultSet.getString("name"));
                        System.out.print("; productGroup: " + resultSet.getString("productGroup"));
                        System.out.print("; subgroup: " + resultSet.getString("subgroup"));
                        System.out.print("; description: " + resultSet.getString("description"));
                        System.out.print("; price: " + resultSet.getDouble("price"));
                        System.out.println();
                    }
                }
            }
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
                e.printStackTrace();
            }
        }
    }
}
