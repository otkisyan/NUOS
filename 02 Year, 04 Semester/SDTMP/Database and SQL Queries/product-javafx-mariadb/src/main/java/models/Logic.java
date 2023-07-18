package models;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Logic {

    public void addProduct(Connection connection, String name, String manufacturer, double price, LocalDate expirationDate, int amount) {

        try (PreparedStatement statement = connection
                .prepareStatement("INSERT INTO product(name, manufacturer, price, amount, expirationDate) " +
                        "VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, name);
            statement.setString(2, manufacturer);
            statement.setDouble(3, price);
            statement.setInt(4, amount);
            statement.setDate(5, Date.valueOf(expirationDate));
            statement.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void removeProduct(Connection connection, int id) {

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = ?")) {

            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    public List<Product> getAllProducts(Connection connection) {

        List<Product> products = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * from product")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product(

                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getInt("amount"));

                products.add(product);
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return products;
    }

    // A - Список товарів для заданого найменування в порядку спадання терміну зберігання
    public List<Product> filterByName(Connection connection, String nameToFind) {

        List<Product> products = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * from product WHERE name = ? ORDER BY expirationDate DESC")) {

            statement.setString(1, nameToFind);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product(

                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getInt("amount"));

                products.add(product);
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return products;
    }

    // B - Список товарів для заданого найменування, ціна яких не перевищує задану
    public List<Product> filterNotExceedPrice(Connection connection, String nameToFind, double price) {

        List<Product> products = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * from product WHERE name = ? AND price <= ?")) {

            statement.setString(1, nameToFind);
            statement.setDouble(2, price);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product(

                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getInt("amount"));

                products.add(product);
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return products;
    }

    // C - Список товарів, термін зберігання яких більше заданого
    public List<Product> filterPeriodLongerThan(Connection connection, LocalDate storagePeriod) {

        List<Product> products = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * from product WHERE expirationDate > ?")) {

            statement.setDate(1, Date.valueOf(storagePeriod));

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product(

                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getInt("amount"));

                products.add(product);
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return products;
    }

    // D - Список товарів, впорядкований за зростанням вартості (кількість * ціна),
    // якщо вартість однакова, то за спаданням ціни;
    public List<Product> sortListByPriceAscending(Connection connection) {

        List<Product> products = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM product ORDER BY amount * price ASC, price DESC")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Product product = new Product(

                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getInt("amount"));

                products.add(product);
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return products;
    }

    // E - Список виробників продуктів, зареєстрованих в програмі;
    public Set<String> getSetOfManufactures(Connection connection) {

        Set<String> manufacturers = new HashSet<>();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT DISTINCT manufacturer FROM product")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                resultSet.getString("manufacturer");
                manufacturers.add(resultSet.getString("manufacturer"));

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return manufacturers;

    }

    // F - Для кожного виробника вивести список продуктів, які він виробляє.
    public Map<String, List<Product>> filterProductsByManufactures(Connection connection) {

        Map<String, List<Product>> productsByManufactures = new HashMap<>();

        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM product ORDER BY manufacturer")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String manufacturer = resultSet.getString("manufacturer");

                Product product = new Product(

                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("manufacturer"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("expirationDate").toLocalDate(),
                        resultSet.getInt("amount"));

                if (!productsByManufactures.containsKey(manufacturer)) {

                    productsByManufactures.put(manufacturer, new ArrayList<>());
                }

                productsByManufactures.get(manufacturer).add(product);

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return productsByManufactures;
    }
}
