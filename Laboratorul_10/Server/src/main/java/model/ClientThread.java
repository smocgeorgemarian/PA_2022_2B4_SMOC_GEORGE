package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class ClientThread extends Thread {
    private final Socket socket;
    private final SimpleServer simpleServer;
    private static final String SRV_STOP = "Server stopped";
    private static final String SRV_EXIT = "Server received the request exit";
    public ClientThread (Socket socket, SimpleServer simpleServer) {
        this.socket = socket ;
        this.simpleServer = simpleServer;
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void run () {
        while (true) {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                String answer;
                boolean hasToStop = false;
                boolean hasToExit = false;
                if (request.equals("stop")) {
                    answer = SRV_STOP;
                    hasToStop = true;
                } else if (request.equals("exit")) {
                    answer = SRV_EXIT;
                    hasToExit = true;
                } else
                    answer = "Server received the request " + request;

                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(answer);
                out.flush();
                if (hasToExit) {
                    closeSocket();
                    break;
                }
                if (hasToStop) {
                    closeSocket();
                    System.exit(0);
                }
            } catch (IOException e) {
                System.err.println("Communication error... " + e);
            }
        }
    }
}