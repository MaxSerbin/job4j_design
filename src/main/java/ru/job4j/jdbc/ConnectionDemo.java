package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import ru.job4j.io.ConfigJdbc;


public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConfigJdbc config = new ConfigJdbc("./data/app.properties");
        config.load();
        Class.forName(config.value("driver"));
        try (Connection connection = DriverManager
                .getConnection(config.value("url"), config.value("login"), config.value("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
