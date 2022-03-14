package solver;

import model.Intersection;

public class NodeInfo {
    private Intersection node;
    private float cost;

    public NodeInfo(Intersection node, float cost) {
        this.node = node;
        this.cost = cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setNode(Intersection node) {
        this.node = node;
    }

    public float getCost() {
        return cost;
    }

    public Intersection getNode() {
        return node;
    }
}
