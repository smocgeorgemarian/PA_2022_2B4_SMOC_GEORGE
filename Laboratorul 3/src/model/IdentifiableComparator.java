package model;

import java.util.Comparator;

public class IdentifiableComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return o1.getMacAdress().compareTo(o2.getMacAdress());
    }
}
