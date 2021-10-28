package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashSet<FileProperty> duplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (duplicates.contains(fileProp)) {
            System.out.println("Duplicate : " + file.toAbsolutePath());
        } else {
            duplicates.add(fileProp);
        }
        return super.visitFile(file, attrs);
    }
}
