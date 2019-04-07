package dao;

import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class Dao {
    Connection connection;

    Dao(String url, String username, String password) throws SQLException {
        try {
            Class.forName(DatabaseConfig.DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url, username, password);
    }
}
