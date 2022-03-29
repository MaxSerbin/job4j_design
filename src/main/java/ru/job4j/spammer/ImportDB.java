package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
             rd.lines().forEach(l -> {
                 String[] data = l.split(";");
                 if (data.length != 2 || data[0].isEmpty() || data[1].isEmpty()) {
                     throw new IllegalArgumentException();
                 }
                 users.add(new User(data[0], data[1]));
             });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users(u_name, email) values (?, ?)")) {
                    ps.setString(1, user.uname);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String uname;
        String email;

        public User(String name, String email) {
            this.uname = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream is = ImportDB.class.getClassLoader().
                getResourceAsStream("spam.properties")) {
            config.load(is);
        }
        ImportDB db = new ImportDB(
                config, "./data/dump.txt");
        db.save(db.load());
    }
}
