package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analyzi {

    public static void unavailable(String source, String target) {
        List<String> lines = new ArrayList<>();
        String original;
        String result = "";
        boolean serverDown = false;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            while ((original = read.readLine()) != null) {
                if ((original.startsWith("500") || (original.startsWith("400")) && !serverDown)) {
                        serverDown = true;
                        result = original.split(" ")[1];
                } else {
                    if ((original.startsWith("300") || (original.startsWith("200")) && serverDown)) {
                        serverDown = false;
                        result = "Сервер не работал с : " + result + " по: " + original.split(" ")[1];
                        lines.add(result);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            lines.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Analyzi.unavailable("./server/server.csv", "./server/unavailable.csv");
    }
}
