package models;

import gui.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    Connection connection;
    final String user = App.dotenv.get("DB_USER");
    final String password = App.dotenv.get("DB_PASSWORD");
    final String host = App.dotenv.get("DB_HOST");
    final String port = App.dotenv.get("DB_PORT");
    final String name = App.dotenv.get("DB_NAME");
    final String jdbcDriver = App.dotenv.get("JDBC_DRIVER");
    final String url = "jdbc:mariadb://" + host + ":" + port + "/" + name;

    private DatabaseConnection() {

    }

    public static DatabaseConnection getInstance() {

        if (instance == null) {

            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName(jdbcDriver);
        connection = DriverManager.getConnection(url, user, password);

        return connection;
    }


}
