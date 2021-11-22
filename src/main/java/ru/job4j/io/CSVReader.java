package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static Path path;
    private static String delimiter;
    private static String out;
    private static String filter;
    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    public CSVReader(ArgsName argsName) {
        path = Path.of(argsName.get("path"));
        delimiter = argsName.get("delimiter");
        out = argsName.get("out");
        filter = argsName.get("filter");
    }

    private void validation(ArgsName argsName) {
        if (argsName.getValues().size() == 0) {
            throw new IllegalArgumentException("args is null");
        }
        if (argsName.getValues().size() != 4) {
            throw new IllegalArgumentException("Ошибка! Укажите ВСЕ аргументы.");
        }
        if (!"stdout".equals(out) && !path.toFile().exists()) {
            throw new IllegalArgumentException("Файл не существует.");
        }
    }

    public static void handle(ArgsName argsName) {
        CSVReader csvr = new CSVReader(argsName);
        csvr.validation(argsName);
        List<Integer> col = new ArrayList<>();
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(path.toFile())))) {
            while (sc.hasNextLine()) {
                String[] strings = sc.nextLine().split(delimiter);
                for (int i = 0; i < strings.length; i++) {
                    if (filter.contains(strings[i])) {
                        col.add(i);
                    }
                }
                for (int j : col) {
                    STRING_BUILDER.append(strings[j]).append(";");
                }
                STRING_BUILDER.deleteCharAt(STRING_BUILDER.length() - 1);
                STRING_BUILDER.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void result(ArgsName argsName) {
        handle(argsName);
        if ("stdout".equals(out)) {
            System.out.println(STRING_BUILDER);
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
                bw.write(STRING_BUILDER.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
       ArgsName argsName = ArgsName.of(args);
       result(argsName);
    }

}
