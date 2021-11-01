package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static final HashMap<FileProperty, List<Path>> map = new HashMap<>();

    public static Map<FileProperty, List<Path>> getDuplicates() {
        Map<FileProperty, List<Path>> rsl = new HashMap<>();
        for (Map.Entry<FileProperty, List<Path>> i : map.entrySet()) {
            if (i.getValue().size() > 1) {
                rsl.put(i.getKey(), i.getValue());
            }
        }
        return rsl;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!map.containsKey(fileProp)) {
             map.put(fileProp, new ArrayList<>(List.of(file.toAbsolutePath())));
        } else {
             map.get(fileProp).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
