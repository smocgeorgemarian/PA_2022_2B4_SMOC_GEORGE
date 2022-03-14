package model;

import java.util.Objects;

public class Street implements Comparable<Street>{
    private String name;
    private float length;
    Intersection start;
    Intersection end;

    public Street(String name, float length, Intersection start, Intersection end) {
        this.name = name;
        this.length = length;
        this.start = start;
        this.end = end;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public void setEnd(Intersection end) {
        this.end = end;
    }

    public void setStart(Intersection start) {
        this.start = start;
    }

    public Intersection getEnd() {
        return end;
    }

    public Intersection getStart() {
        return start;
    }

    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }

    @Override
    public int compareTo(Street o) {
        if (this.length < o.length)
            return -1;
        if (this.length > o.length)
            return 1;
        if (this.name.compareTo(o.name) != 0)
            return this.name.compareTo(o.name);
        if (this.getStart().compareTo(o.getStart()) != 0)
            return this.getStart().compareTo(o.getStart());
        if (this.getEnd().compareTo(o.getEnd()) != 0)
            return this.getEnd().compareTo(o.getEnd());
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Street street = (Street) o;
        return Float.compare(street.length, length) == 0 && Objects.equals(name, street.name)
                && Objects.equals(start, street.start) && Objects.equals(end, street.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length, start, end);
    }
}
