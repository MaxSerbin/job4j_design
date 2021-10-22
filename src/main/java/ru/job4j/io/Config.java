package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();
    private String[] rsl;

    public Config(final String path) {
        this.path = path;
    }

    public boolean chek() {
        boolean result = false;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String orig;
            while ((orig = read.readLine()) != null) {
                if (!orig.isEmpty() && (orig.charAt(0) != '#')) {
                    rsl = orig.split("=");
                    result = true;
                    if (rsl.length != 2 || !orig.contains("=") || !orig.contains("-") || orig.contains("==")) {
                        System.out.println("Ошибка ! Нарушение шаблона ключ=значение.");
                        throw new IllegalArgumentException("Ошибка ! Нарушение шаблона ключ=значение.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void load() {
      if (chek()) {
          values.put(rsl[0], rsl[1]);
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

    public static void main(String[] args) {
        System.out.println(new Config("./data/pair_with_comment.properties"));
    }
}
