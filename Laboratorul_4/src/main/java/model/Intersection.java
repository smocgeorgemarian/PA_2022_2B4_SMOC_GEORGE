package model;

import java.util.Objects;

public class Intersection implements Comparable<Intersection>{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Intersection(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Intersection o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
