package com.company.homework;

import java.util.Objects;

public class ComputerLab extends Room {
    private String operatingSystem;

    public ComputerLab(String name, int capacity, String operatingSystem) {
        this.name = name;
        this.capacity = capacity;
        this.operatingSystem = operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public String toString() {
        return "ComputerLab{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComputerLab that = (ComputerLab) o;
        return Objects.equals(operatingSystem, that.operatingSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), operatingSystem);
    }
}
