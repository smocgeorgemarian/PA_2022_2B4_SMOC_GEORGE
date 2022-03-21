package app;

import model.*;
import solver.Algorithm;
import solver.DijkstraAlgorithm;
import solver.RoyWarshallAlgorithm;

import java.util.List;

public class Main {
    /*
    Add nodes from the example
    */
    private static void createNetwork(Network network) {
        Node node = new Computer("Computer A", "00:FF:FF:AB:BB:AA",
                "2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26a", 256);
        Node node1 = new Router("Router A", "00:FF:FF:AB:BB:AB",
                "2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26b");
        Node node2 = new Switch("Switch A", "00:FF:FF:AB:BB:AC");
        Node node3 = new Switch("Switch B", "00:FF:FF:AB:BB:AD");
        Node node4 = new Router("Router B", "00:FF:FF:AB:BB:AE",
                "2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26e");
        Node node5 = new Computer("Computer B", "00:FF:FF:AB:BB:AF" ,
                "2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26f", 512);
        Edge edge = new Edge(10, (float)0.3);
        node.setCost(node1, edge);
        edge = new Edge(50, (float)0.4);
        node.setCost(node2, edge);
        edge = new Edge(20, (float)0.4);
        node1.setCost(node2, edge);
        edge = new Edge(20, (float)0.4);
        node1.setCost(node3, edge);
        edge = new Edge(10, (float)0.4);
        node1.setCost(node4, edge);
        edge = new Edge(20, (float)0.4);
        node2.setCost(node3, edge);
        edge = new Edge(30, (float)0.4);
        node3.setCost(node4, edge);
        edge = new Edge(10, (float)0.4);
        node3.setCost(node5, edge);
        edge = new Edge(20, (float)0.4);
        node4.setCost(node5, edge);

        network.addNode(node);
        network.addNode(node1);
        network.addNode(node2);
        network.addNode(node3);
        network.addNode(node4);
        network.addNode(node5);
        Computer computer = new Computer("Computer B", "00:FF:FF:AB:BB:AF" ,
                "2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26f", 512);
        // test the default method
        System.out.println(computer.getStorageCapacity("byte"));
    }
    public static void main(String[] args) {
        Network network = new Network();
        createNetwork(network);
        System.out.println("Initial network:\n" + network);
        network.setIdentifiablesToPrintable();
        Algorithm royWarshallAlgorithm = new RoyWarshallAlgorithm(network);
        // create a network in which paths become edges
        Network contractedNetwork = royWarshallAlgorithm.solve();
        System.out.println("Network where paths > 2 are edges and is made of paths with start node and end node" +
                " identifiable:\n" + contractedNetwork);

        List<Node> nodes = network.getNodes();
        Node startNode = nodes.get(0);
        Node endNode = nodes.get(nodes.size() - 1);
        // Apply Dijkstra's algorithm on start node to get the cost to get to end node
        Algorithm dijkstraAlgorithm = new DijkstraAlgorithm(network, startNode, endNode);
        contractedNetwork = dijkstraAlgorithm.solve();
        System.out.println("Network considering only the best path from start node to end node:\n" + contractedNetwork);
    }
}
