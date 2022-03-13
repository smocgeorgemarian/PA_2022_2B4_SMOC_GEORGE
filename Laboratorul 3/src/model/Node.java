package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public abstract class Node implements Comparable<Node>{
    protected String name;
    protected Map<Node, Edge> cost = new HashMap<>();
    protected String macAdress;

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setCost(Node node, Edge edge) {
        cost.put(node, edge);
    }

    public Edge getCost(Node node) {
        return cost.get(node);
    }

    public void setCostMap(Map<Node, Edge> cost) {
        this.cost = cost;
    }

    public Map<Node, Edge> getCostMap() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Avoid showing too much data about a node representing a neighbour (don't show his adjacency list)
    protected String getCostAdjacencyList() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        int numberOfEntries = 0;
        for (Map.Entry<Node, Edge> costEntry : cost.entrySet()) {
            String key = costEntry.getKey().name;
            Edge edge = costEntry.getValue();
            if (numberOfEntries > 0)
                sb.append(", ");
            sb.append('\'').append(key).append("': ").append(edge);
            numberOfEntries++;
        }
        sb.append('}');
        return sb.toString();
    }

    public void clearCost() {
        cost.clear();
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", cost=" + getCostAdjacencyList() +
                ", macAdress='" + macAdress + '\'' +
                '}';
    }

    public int compareTo(Node other) {
        if (this == other) return 0;
        if (other == null ) throw new NullPointerException();
        return this.name.compareTo((other).name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name) && Objects.equals(cost, node.cost) && Objects.equals(macAdress, node.macAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, macAdress);
    }
}
