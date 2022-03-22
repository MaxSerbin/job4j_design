package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ConfigJdbc {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public ConfigJdbc(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String str = read.readLine(); str != null; str = read.readLine()) {
                if (str.length() == 0 || str.startsWith("#")) {
                    continue;
                }
                String[] keyVal = str.split("=");
                if (keyVal.length != 2) {
                    throw new IllegalArgumentException();
                }
                values.put(keyVal[0], keyVal[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
