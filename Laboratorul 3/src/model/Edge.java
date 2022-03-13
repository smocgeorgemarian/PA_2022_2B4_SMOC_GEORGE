package model;

public record Edge(float cost, float probability) {
    @Override
    public float probability() {
        return probability;
    }

    @Override
    public float cost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "cost=" + cost +
                ", probability=" + probability +
                '}';
    }
}
