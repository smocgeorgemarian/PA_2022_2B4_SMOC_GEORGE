package model;

import com.jcraft.jsch.JSchException;
import graphic.GraphModelator;
import test.SFTPFileTransfer;
import test.XMLCreator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class SimpleServer {
    public static final int PORT = 8100;
    public static final int TIMEOUT = 6000;

    private boolean isOpen = true;
    public synchronized void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public SimpleServer() {
        List<List<Integer>> adjacencyList = GraphModelator.getAdjacencyList();
        XMLCreator testSVGGen = new XMLCreator(adjacencyList);
        try {
            testSVGGen.setup();
            SFTPFileTransfer.sendXML();
        }
        catch (IOException | JSchException e) {
            e.printStackTrace();
        }
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(TIMEOUT);
            while (isOpen) {
                System.out.println("Waiting for a client ...");
                try {
                    Socket socket = serverSocket.accept();
                    socket.setSoTimeout(TIMEOUT);
                    new ClientThread(socket, this).start();
                }
                catch (SocketTimeoutException e) {
                    System.out.println("No client found");
                }
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        }
    }
    public static void main ( String [] args ) {
        new SimpleServer();
    }
}
