package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashSet<FileProperty> check = new HashSet<>();
    private static final List<FileProperty> DUPLICATES = new ArrayList<>();

    public static void getDuplicates() {
        for (FileProperty duplicate : DUPLICATES) {
            System.out.println("Duplicate : " + duplicate);
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!check.contains(fileProp)) {
             check.add(fileProp);
        } else {
            DUPLICATES.add(fileProp);
        }
        return super.visitFile(file, attrs);
    }
}
