package solver;

import model.*;

import java.util.*;

public class DijkstraAlgorithm implements Algorithm{
    private final List<Node> nodeList;
    private final Node startNode;
    private final Node endNode;
    private static final float INF_VALUE = 9999999;
    private static final int NO_BEFORE = -1;
    private final int sizeNodeList;

    public DijkstraAlgorithm(Network network, Node startNode, Node endNode) {
        this.nodeList = network.getNodes();
        this.sizeNodeList = this.nodeList.size();
        this.startNode = startNode;
        this.endNode = endNode;
    }

    private Map<Node, Integer> getNormalisation() {
        Map<Node, Integer> normalisation = new HashMap<>();
        for (int i = 0 ; i < this.nodeList.size(); i++) {
            normalisation.put(this.nodeList.get(i), i);
        }
        return normalisation;
    }

    private List<List<NodeInfo>> getAdjacencyList(Map<Node, Integer> normalisation) {
        List<List<NodeInfo>> adjacencyList = new ArrayList<>();
        for (Node value : this.nodeList) {
            List<NodeInfo> neighbours = new ArrayList<>();
            for (Map.Entry<Node, Edge> costEntry : value.getCostMap().entrySet()) {
                int nodeIndex = normalisation.get(costEntry.getKey());
                float cost = costEntry.getValue().probability();
                NodeInfo nodeInfo = new NodeInfo(nodeIndex, cost);
                neighbours.add(nodeInfo);
            }
            adjacencyList.add(neighbours);
        }
        return adjacencyList;
    }
    // Compute relationship between new nodes, considering only the best path from start node to end node
    private List<Node> getNewNodes(float[] costArray, int[] before, int endIndex) {
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
        while (before[endIndex] != NO_BEFORE) {
            int bef = before[endIndex];
            float prob = (float) Math.exp(costArray[endIndex] - costArray[bef]);
            Edge edge = new Edge(-1, prob);
            newNodes.get(bef).setCost(newNodes.get(endIndex), edge);
            endIndex = before[endIndex];
        }
        return newNodes;
    }

    @Override
    public Network solve() {
        Map<Node, Integer> normalisation = getNormalisation();
        List<List<NodeInfo>> adjacencyList = getAdjacencyList(normalisation);
        PriorityQueue<NodeInfo> priorityQueue = new PriorityQueue<>(this.sizeNodeList, new NodeInfoComparator());
        int startIndex = normalisation.get(this.startNode);

        float[] costArray = new float[this.sizeNodeList];
        Arrays.fill(costArray, INF_VALUE);
        costArray[startIndex] = 0;

        boolean[] used = new boolean[this.sizeNodeList];
        used[startIndex] = true;

        NodeInfo nodeInfo = new NodeInfo(startIndex, 0);
        priorityQueue.add(nodeInfo);

        int[] before = new int[this.sizeNodeList];
        Arrays.fill(before, NO_BEFORE);
        while (!priorityQueue.isEmpty()) {
            NodeInfo actualNode = priorityQueue.poll();

            int nodeIndex = actualNode.getNodeIndex();
            used[nodeIndex] = false;
            float cost = actualNode.getCost();
            // Visit all neighbours and update cost where necessary
            for (int i = 0; i < adjacencyList.get(nodeIndex).size(); i++) {
                NodeInfo toNeighbour = adjacencyList.get(nodeIndex).get(i);
                int nodeIndexNeighbour = toNeighbour.getNodeIndex();
                float costNeighbour = toNeighbour.getCost();
                if (cost + Math.log(costNeighbour) < costArray[nodeIndexNeighbour]) {
                    costArray[nodeIndexNeighbour] = cost + (float) Math.log(costNeighbour);
                    before[nodeIndexNeighbour] = nodeIndex;

                    if (!used[nodeIndexNeighbour]) {
                        used[nodeIndexNeighbour] = true;
                        NodeInfo nodeInfo1 = new NodeInfo(nodeIndexNeighbour, costArray[nodeIndexNeighbour]);
                        priorityQueue.add(nodeInfo1);
                    }
                }
            }
        }
        int endIndex = normalisation.get(this.endNode);
        List<Node> newNodes = getNewNodes(costArray, before, endIndex);
        return new Network(newNodes);
    }

}
