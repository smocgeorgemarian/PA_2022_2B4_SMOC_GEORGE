package solver;

import java.util.Comparator;

public class NodeInfoComparator implements Comparator<NodeInfo> {
    @Override
    public int compare(NodeInfo o1, NodeInfo o2) {
        return Float.compare(o1.getCost(), o2.getCost());
    }
}
