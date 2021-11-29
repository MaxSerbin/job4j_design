package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (!str.contains("?msg=Hello") && !str.contains("?msg=Exit")) {
                            out.write("Hello, dear friend. ".getBytes());
                            out.write(doubleMsg(str).getBytes());
                            throw new IOException("Unsupported message.");
                        }
                        if (str.contains("?msg=Exit")) {
                            out.write("Server is closed. ".getBytes());
                            server.close();
                            break;
                        }
                        if (str.contains("?msg=Hello")) {
                            out.write("Hello, dear friend. ".getBytes());
                            out.write(" Hello ! ".getBytes());
                            break;
                        }
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }

    private static String doubleMsg(String str) {
        int start = str.indexOf("?");
        int end = str.lastIndexOf(" ");
        String s = "What a stupid - ";
        String[] strings = str.substring(start + 1, end).split("=");
            String msg = strings[1];
          if (!("Hello".equals(msg)) && !("Exit".equals(msg))) {
              System.out.println(msg);
          }
          return s + msg + " ?";
    }

}
