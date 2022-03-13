package solver;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoyWarshallAlgorithm implements Algorithm {
    private final List<Node> nodeList;
    int sizeNodeList;

    public RoyWarshallAlgorithm(Network network) {
        this.nodeList = network.getNodes();
        this.sizeNodeList = this.nodeList.size();
    }

    private float[][] getCostMatrix() {
        Map<Node, Integer> indexOfNode = new HashMap<>();
        for (int i = 0; i < this.sizeNodeList; i++)
            indexOfNode.put(this.nodeList.get(i), i);

        float[][] costMatrix = new float[this.sizeNodeList][this.sizeNodeList];
        for (int i = 0; i < this.sizeNodeList; i++) {
            Node node = this.nodeList.get(i);
            for (Map.Entry<Node, Edge> costEntry : node.getCostMap().entrySet()) {
                int j = indexOfNode.get(costEntry.getKey());
                costMatrix[i][j] = (costEntry.getValue()).cost();
            }
        }
        return costMatrix;
    }

    private List<Node> getNewNodes() {
        List<Node> newNodes = new ArrayList<>();
        for (Node node: this.nodeList) {
            Node newNode;
            if (node instanceof Computer computer)
                newNode = new Computer(computer.getName(), computer.getMacAdress(), computer.getAddress(),
                        computer.getStorageCapacity());
            else if (node instanceof Router router)
                newNode = new Router(router.getName(), router.getMacAdress(), router.getAddress());
            else
                newNode = new Switch(node.getName(), node.getMacAdress());
            newNodes.add(newNode);
        }
        return newNodes;
    }

    void setCostForNodeList(List<Node> newNodes, float[][] costMatrix) {
        for (int i = 0; i < this.sizeNodeList; i++) {
            Node fNode = newNodes.get(i);
            if (fNode instanceof Identifiable) {
                fNode.clearCost();
                for (int j = 0; j < this.sizeNodeList; j++)
                    if (costMatrix[i][j] > 0) {
                        Node lNode = newNodes.get(j);
                        if (lNode instanceof Identifiable) {
                            Edge edge = new Edge(costMatrix[i][j], -1);
                            fNode.setCost(lNode, edge);
                        }
                    }
            }
        }
    }

    List<Node> getNewNodesFiltered(List<Node> newNodes) {
        List<Node> newNodesFiltered = new ArrayList<>();
        for (Node node: newNodes) {
            if (node instanceof Identifiable)
                newNodesFiltered.add(node);
        }
        return newNodesFiltered;
    }

    @Override
    public Network solve() {
        // Try to minimize cost in O(N^3)
        float[][] costMatrix = getCostMatrix();
        for (int k = 0; k < this.sizeNodeList; k++)
            for (int i = 0; i < this.sizeNodeList; i++)
                for(int j = 0; j < this.sizeNodeList; j++) {
                    if (costMatrix[i][k] > 0 && costMatrix[k][j] > 0 &&
                            (costMatrix[i][j] > costMatrix[i][k] + costMatrix[k][j] || costMatrix[i][j] == 0) && i != j)
                        costMatrix[i][j] = costMatrix[i][k] + costMatrix[k][j];
                }
        // Create a new List of nodes for a new network so the initial one is not modified
        List<Node> newNodes = getNewNodes();
        setCostForNodeList(newNodes, costMatrix);
        // Create a network consisting of the nodes that are identifiable only
        List<Node> newNodesFiltered = getNewNodesFiltered(newNodes);
        return new Network(newNodesFiltered);
    }
}
