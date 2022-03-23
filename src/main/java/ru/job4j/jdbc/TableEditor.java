package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        try (FileInputStream f = new FileInputStream("./data/table_editor.properties")) {
            properties.load(f);
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager
                    .getConnection(properties.getProperty("url"), properties.getProperty("login"),
                            properties.getProperty("password"));
        }
    }

    private void doIt(String script) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(script);
        }
    }

    public void createTable(String tableName) throws SQLException {
        doIt(String.format("create table if not exists %s(%s)",
                tableName,
                "id serial primary key")
        );
    }

    public void dropTable(String tableName) throws SQLException {
        doIt(String.format("drop table %s", tableName));
        System.out.println("Table dropped.");
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        doIt(String.format("alter table %s add %s %s null", tableName, columnName, type));
        System.out.println("Column added.");

    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        doIt(String.format("alter table %s drop column %s", tableName, columnName));
        System.out.println("Column dropped.");
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        doIt(String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName));
        System.out.println("Column renamed.");
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tbe = new TableEditor(new Properties());
        tbe.createTable("car");
        System.out.println(getTableScheme(tbe.connection, "car"));
        tbe.addColumn("car", "model", "varchar(255)");
        tbe.addColumn("car", "ear", "int");
        System.out.println(getTableScheme(tbe.connection, "car"));
        tbe.renameColumn("car", "ear", "year");
        System.out.println(getTableScheme(tbe.connection, "car"));
        tbe.dropColumn("car", "year");
        System.out.println(getTableScheme(tbe.connection, "car"));
        tbe.dropTable("car");
    }
}
