package generator;

import model.Intersection;

import java.util.ArrayList;
import java.util.List;

public class CycleSolution {
    private List<Intersection> nodes = new ArrayList<>();

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
