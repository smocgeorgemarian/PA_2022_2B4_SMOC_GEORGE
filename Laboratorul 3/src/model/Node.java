package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public abstract class Node implements Comparable<Node>{
    protected String name;
    protected Map<Node, Integer> cost = new HashMap<>();
    protected String macAdress;


    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setCost(Node node, int value) {
        cost.put(node, value);
    }

    public Integer getCost(Node node) {
        return cost.get(node);
    }

    public void setCostMap(Map<Node, Integer> cost) {
        this.cost = cost;
    }

    public Map<Node, Integer> getCostMap() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", macAddress='" + macAdress + '\'' +
                '}';
    }

    public int compareTo(Node other) {
        if (this == other) return 0;
        if (other == null ) throw new NullPointerException();
        if (!( other instanceof Node ))
            throw new ClassCastException ("Uncomparable objects!");
        return this.name.compareTo(((Node)other).name);
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
