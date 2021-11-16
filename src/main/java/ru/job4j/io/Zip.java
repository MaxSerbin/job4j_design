package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void validation(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Ошибка ! Укажите исходную папку , нужное расширение и" +
                    "конечный файл.");
        }
        for (String s : args) {
            String[] rsl = s.split("=");
            if (rsl.length != 2 || rsl[0].isEmpty() || !rsl[0].startsWith("-")) {
                throw new IllegalArgumentException("Ошибка ! Нарушение шаблона ключ=значение.");
            }
            if ("-d".equals(rsl[0])) {
                File file = new File(rsl[1]);
                if (!file.exists() || !file.isDirectory()) {
                    throw new IllegalArgumentException("Директория не существует или не является директорией.");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        ArgsName argsName = ArgsName.of(args);
        Path dir = Path.of(argsName.get("d"));
        String ex = argsName.get("e");
        List<Path> list = Search.search(dir, p -> !p.toFile().getName().endsWith(ex));
        File output = new File(argsName.get("o"));
        packFiles(list, output);
    }
}
