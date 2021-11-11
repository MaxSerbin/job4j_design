package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
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

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
