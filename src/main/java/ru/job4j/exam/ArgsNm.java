package ru.job4j.exam;

import java.util.HashMap;
import java.util.Map;

public class ArgsNm {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void check(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("args is null");
        }
        for (String i : args) {
            String[] rsl = i.split("=");
            if (rsl.length != 2 || rsl[0].isEmpty() || !rsl[0].startsWith("-")) {
                throw new IllegalArgumentException("Ошибка ! Нарушение шаблона ключ=значение.");
            }
        }
    }

    private void parse(String[] args) {
        check(args);
        for (String i : args) {
            String[] rsl = i.split("=");
            values.put(rsl[0].substring(1), rsl[1]);
        }
    }

    public static ArgsNm of(String[] args) {
        ArgsNm names = new ArgsNm();
        names.parse(args);
        return names;
    }
}
