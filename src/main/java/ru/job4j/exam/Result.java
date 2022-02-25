package ru.job4j.exam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Result {
    private SearchFile searchFile;
    private ArgsNm argsNm;
    private WriteToFile writeToFile;

    public Result(String[] args) throws IOException {
        this.argsNm = ArgsNm.of(args);
        this.writeToFile = new WriteToFile();
        toFile();
    }

    private Predicate<Path> getPred() {
        Predicate<Path> predicate = null;
        var name = argsNm.get("n");
        var type = argsNm.get("t");
        if ("name".equals(type)) {
            predicate = p -> p.toFile().getName().equals(name);
        } else if ("mask".equals(type)) {
            var change = name.replaceAll("\\*", ".\\*")
                    .replaceAll("\\?", ".\\?")
                    .replaceAll("\\.", ".\\.");
            Pattern pattern = Pattern.compile(change);
            predicate = p -> pattern.matcher(p.toFile().getName()).matches();
        } else if ("regex".equals(type)) {
            Pattern pattern = Pattern.compile(name);
            predicate = p -> pattern.matcher(p.toFile().getName()).matches();
        }
        return predicate;
    }

    private List<Path> searcher(Path root, Predicate<Path> condition) throws IOException {
        this.searchFile = new SearchFile(getPred());
        var x = argsNm.get("d");
        Files.walkFileTree(Path.of(x), searchFile);
        return searchFile.getPaths();
    }

    private void toFile() throws IOException {
        writeToFile.writeFiles(searcher(Paths.get(argsNm.get("d")), getPred()), argsNm.get("o"));
    }

    public static void main(String[] args) throws IOException {
        args = new String[]{"-d=c:/", "-n=*.txt", "-t=mask", "-o=log.txt"};
        new Result(args);
    }
}
