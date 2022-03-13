package model;

public record Edge(float cost, float probability) {
    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                ", probability=" + probability +
                '}';
    }
}
