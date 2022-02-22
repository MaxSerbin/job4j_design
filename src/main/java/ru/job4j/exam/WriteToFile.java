package ru.job4j.exam;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class WriteToFile {
    public void writeFiles(List<Path> source, String target) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (Path path : source) {
                out.write(path.toFile().getPath());
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
