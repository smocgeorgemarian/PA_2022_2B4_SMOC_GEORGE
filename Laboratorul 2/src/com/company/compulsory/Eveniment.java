package com.company.compulsory;

import java.util.Objects;

public class Eveniment {
    private String name;
    private int size;
    private int startTime;
    private int endTime;

    public Eveniment(String name, int size, int startTime, int endTime) {
        this.name = name;
        this.size = size;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getendTime() {
        return endTime;
    }

    public int getstartTime() {
        return startTime;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Eveniment{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eveniment eveniment = (Eveniment) o;
        return size == eveniment.size && startTime == eveniment.startTime && endTime == eveniment.endTime && Objects.equals(name, eveniment.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, startTime, endTime);
    }
}
