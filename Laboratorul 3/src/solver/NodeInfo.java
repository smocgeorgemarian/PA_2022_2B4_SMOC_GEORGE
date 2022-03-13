package solver;

import java.util.Comparator;

public class NodeInfo {
    private int nodeIndex;
    private float cost;
    public NodeInfo(int nodeIndex, float cost) {
        this.nodeIndex = nodeIndex;
        this.cost = cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public float getCost() {
        return cost;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }
}
