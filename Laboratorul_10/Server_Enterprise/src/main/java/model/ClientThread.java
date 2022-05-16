package model;

import exception.InvalidNumberOfArgs;
import manager.FactoryManager;
import manager.RegisterCommand;
import manager.StateManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;


class ClientThread extends Thread {
    private final Socket socket;
    public ClientThread (Socket socket) {
        this.socket = socket ;
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
        EntityManagerFactory emf = FactoryManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        StateManager stateManager = new StateManager(socket, em);
        while (true) {
            try {
                stateManager.parseInput();
                stateManager.execute();
                stateManager.setOutputPrintable();

                if (stateManager.getHasToExit()) {
                    closeSocket();
                    break;
                }
                if (stateManager.getHasToStop()) {
                    closeSocket();
                    System.exit(0);
                }
            } catch (IOException | InvalidNumberOfArgs e) {
                System.err.println("Communication error... " + e);
            }
        }
    }
}