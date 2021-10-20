package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;

public class AnalyziTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            out.write("200 10:56:01\n"
                  + "\n" + "500 10:57:01\n"
                  + "\n" + "400 10:58:01\n"
                  + "\n" + "200 10:59:01\n"
                  + "\n" + "500 11:01:02\n"
                  + "\n" + "200 11:02:02");
        }
        Analyzi.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat(rsl.toString(),
                is("Сервер не работал с : 10:57:01 по: 10:59:01Сервер не работал с : 11:01:02 по: 11:02:02"));
    }
}