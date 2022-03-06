package lab.comparator;

import lab.solver.NodeInfo;

import java.util.Comparator;
/**
 * Class that implements <code>interface Comparator</code>.
 * Compares 2 <code>NodeInfo</code> objects based on their saturation degree, degree and finally index
 * (respecting this order).
 * <p>
 * Used to keep <code>NodeInfo</code> objects ordered in <code>PriorityQueue</code> (biggest object is put in front).
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 * @see Comparator
 */
public class NodesComparator implements Comparator<NodeInfo> {
    /**
     * Compares two <code>NodeInfo</code> objects and returns value needed by <code>PriorityQueue</code> to sort them
     * in descending order.
     * @param o1 first <code>NodeInfo</code> object to be compared
     * @param o2 second <code>NodeInfo</code> object to be compared
     * @return 	&#60;0 in case first object is bigger
     * <p>
     * &#62;0 in case second object is bigger
     * <p>
     * 0 otherwise
     */
    @Override
    public int compare(NodeInfo o1, NodeInfo o2) {
        if (o1.saturationDegree() > o2.saturationDegree()) return -1;
        if (o1.saturationDegree() < o2.saturationDegree()) return 1;
        if (o1.degree() > o2.degree()) return -1;
        if (o1.degree() < o2.degree()) return 1;
        return Integer.compare(o2.index(), o1.index());
    }
}
