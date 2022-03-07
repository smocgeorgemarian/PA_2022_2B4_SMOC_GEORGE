package app;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void createNetwork(Network network) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Computer("Computer A", "00:FF:FF:AB:BB:AA", "2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26a", 256));
        nodes.add(new Router("Router A", "00:FF:FF:AB:BB:AB", "2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26b"));
        nodes.add(new Switch("Switch A", "00:FF:FF:AB:BB:AC"));
        nodes.add(new Switch("Switch B", "00:FF:FF:AB:BB:AD"));
        nodes.add(new Router("Router B", "00:FF:FF:AB:BB:AE", "2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26e"));
        nodes.add(new Computer("Computer B", "00:FF:FF:AB:BB:AF" ,"2a02:2f0e:6009:9f00:ccb2:9d92:ae9b:e26f", 512));
        network.setNodes(nodes);
    }
    public static void main(String[] args) {
        Network network = new Network();
        createNetwork(network);
        System.out.println(network);
    }
}
