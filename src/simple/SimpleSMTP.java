package simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSMTP {
    public static void main(String[] args) {
        // default port
        int port = 25;

        // used to create a server-side socket
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("smtp server is listening on port " + port);

            // keeps accepting and handling client connections indefinitely, until error happens
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected: " + clientSocket.getInetAddress());

            }
        } catch (IOException error) {
            System.out.println("exception occurred: " + error.getMessage());
        }
    }
// TO BE CONTINUED...
}
