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

    private Predicate<String> getPred() {
        Predicate<String> predicate = null;
        if (argsNm.get("t").equals("name")) {
            String name = nameBuilder(argsNm.get("n"));
            Pattern pattern = Pattern.compile(name);
            predicate = pattern.asPredicate();
        } else if (argsNm.get("t").equals("mask")) {
            String mask = argsNm.get("n");
            String[] s = mask.split("\\*");
            String rsl = s[0];
            Pattern pattern = Pattern.compile(rsl);
            predicate = pattern.asPredicate();
        } else if (argsNm.get("t").equals("regex")) {
                String reg = getRegex(argsNm.get("n"));
                Pattern pattern = Pattern.compile(reg);
                predicate = pattern.asPredicate();
        }
        return predicate;
    }

    private static String nameBuilder(String str) {
        return "^" + str + ".[a-z]+";
    }

    private static String getRegex(String mask) {
        var builder = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char symbol = mask.charAt(i);
            if (symbol == '*') {
                builder.append(".*");
            } else if (symbol == '.') {
                builder.append("\\.");
            } else {
                builder.append(symbol);
            }
        }
        return builder.toString();
    }

    private List<Path> searcher(Path root, Predicate<String> condition) throws IOException {
        this.searchFile = new SearchFile(getPred());
        var x = argsNm.get("d");
        Files.walkFileTree(Path.of(x), searchFile);
        return searchFile.getPaths();
    }

    private void toFile() throws IOException {
        writeToFile.writeFiles(searcher(Paths.get(argsNm.get("d")), getPred()), argsNm.get("o"));
    }

    public static void main(String[] args) throws IOException {
        args = new String[]{"-d=c:/", "-n=.txt", "-t=mask", "-o=log.txt"};
        new Result(args);
    }
}
