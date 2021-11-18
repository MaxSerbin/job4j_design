package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        boolean rsl = true;
        String question;
        do {
            question = scan.nextLine();
            log.add("User: " + question);
            if (OUT.equals(question)) {
                rsl = false;
            }
            if (STOP.equals(question)) {
                rsl = false;
            }
            if (CONTINUE.equals(question)) {
                rsl = true;
            }
            if (rsl) {
                String answer = readPhrases().get(rand.nextInt(readPhrases().size()));
                log.add("Bot: " + answer);
                System.out.println(answer);
            }
        } while (!OUT.equals(question));
                saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            in.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chat/chatlog.txt", "./chat/answers.txt");
        cc.run();
    }
}
