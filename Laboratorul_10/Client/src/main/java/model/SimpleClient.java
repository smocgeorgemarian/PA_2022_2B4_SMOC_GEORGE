package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {
    private static final String serverAddress = "127.0.0.1"; // The server's IP address
    private static final String SRV_STOP = "[answer] Command stop executed successfully";
    private static final String SRV_EXIT = "[answer] Command exit executed successfully";
    private static final int PORT = 8100; // The server's port
    public static void main (String[] args) throws IOException {
        try (   Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (
                        new InputStreamReader(socket.getInputStream())) ) {

            Scanner console = new Scanner(System.in);
            while (true) {
                String request = console.nextLine();
                out.println(request);

                String response = in.readLine();
                System.out.println(response);
                if (response.equals(SRV_STOP) || response.equals(SRV_EXIT))
                    return;
            }
        } catch (UnknownHostException | SocketException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
