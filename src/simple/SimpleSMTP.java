package simple;

import java.io.*;
import java.net.*;

public class SimpleSMTP {
    public static void main(String[] args) {
        int port = 25; // default port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("listening on port " + port);

            // accepting client connections 
            while (true) {
                Socket clientS = serverSocket.accept();
                System.out.println("client connected: " + clientS.getInetAddress());

                handleClient(clientS);
            }
        } catch (IOException error) {
            System.out.println("exception occurred: " + error.getMessage());
        }
    }
    private static void handleClient(Socket s) {
        try{
        // read from the client's input stream
        InputStreamReader inputStreamReader = new InputStreamReader(s.getInputStream());
        BufferedReader reader = new BufferedReader(inputStreamReader);
        // send data output stream
        PrintWriter writer = new PrintWriter(s.getOutputStream(), true);

            writer.println("220 server ready");

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("client sent: " + message);

                if (message.startsWith("HELO") || message.startsWith("EHLO")) {
                    writer.println("250 Hello " + s.getInetAddress());
                } else if (message.startsWith("MAIL FROM:")) {
                    writer.println("250 OK");
                } else if (message.startsWith("RCPT TO:")) {
                    writer.println("250 OK");
                } else if (message.equals("DATA")) {
                    writer.println("250 OK");
                } else if (message.equals("QUIT")) {
                    writer.println("221 Bye");
                    break;
                } else {
                    writer.println("500 Not recognized");
                }
            }

            s.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}

