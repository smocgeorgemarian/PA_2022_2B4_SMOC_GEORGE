package generator;

import model.Intersection;

import java.util.ArrayList;
import java.util.List;

public class CycleSolution {
    List<Intersection> nodes = new ArrayList<>();

    public CycleSolution(List<Intersection> nodes) {
        this.nodes = nodes;
    }

    public CycleSolution() {

    }

    public void addNode(Intersection node) {
        nodes.add(node);
    }

    @Override
    public String toString() {
        return "CycleSolution{" +
                "nodes=" + nodes +
                '}';
    }
}
