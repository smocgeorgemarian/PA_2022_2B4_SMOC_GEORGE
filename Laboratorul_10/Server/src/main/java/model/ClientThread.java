package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class ClientThread extends Thread {
    private final Socket socket;
    private final SimpleServer simpleServer;
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
                if (request.equals("stop")) {
                    answer = "Server stopped";
                    hasToStop = true;
                } else if (request.equals("close")) {
                    answer = "Server received the request " + request;
                    hasToStop = true;
                } else
                    answer = "Server received the request " + request;

                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(answer);
                out.flush();
                if (hasToStop) {
                    closeSocket();
                    return;
                }
            } catch (IOException e) {
                System.err.println("Communication error... " + e);
            }
        }
    }
}