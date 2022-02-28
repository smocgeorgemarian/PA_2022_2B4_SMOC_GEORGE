package com.company.homework;

import java.util.Objects;

public class Eveniment {
    private String name;
    private int size, start_time, end_time;

    public Eveniment(String name, int size, int start_time, int end_time) {
        this.name = name;
        this.size = size;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnd_time() {
        return end_time;
    }

    public int getStart_time() {
        return start_time;
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
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eveniment eveniment = (Eveniment) o;
        return size == eveniment.size && start_time == eveniment.start_time && end_time == eveniment.end_time && Objects.equals(name, eveniment.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, start_time, end_time);
    }
}
