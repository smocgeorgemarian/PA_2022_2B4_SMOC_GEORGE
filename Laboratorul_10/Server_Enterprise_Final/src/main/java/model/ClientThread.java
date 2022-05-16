package model;

import manager.FactoryManager;
import manager.StateManager;
import repository.FriendRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;


class ClientThread extends Thread {
    private final Socket socket;
    private final SimpleServer simpleServer;
    public ClientThread (Socket socket, SimpleServer simpleServer) {
        this.socket = socket;
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
                    simpleServer.setIsOpen(false);
                    return;
                }
            } catch (IOException e) {
                if (e instanceof SocketTimeoutException && stateManager.isLoggedIn()) {
                    closeSocket();
                    System.err.println("Error occoured: " + e.getMessage());
                    return;
                }
                if (e instanceof SocketTimeoutException)
                    continue;
                System.err.println("Error occoured: " + e.getMessage());
            }
        }
    }
}