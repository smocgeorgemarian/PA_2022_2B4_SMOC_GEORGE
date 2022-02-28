package com.company.homework;

import java.util.Objects;

public class LectureHall extends Room {
    private boolean videoProjectorExists;

    public LectureHall(String name, int capacity, boolean videoProjectorExists) {
        this.name = name;
        this.capacity = capacity;
        this.videoProjectorExists = videoProjectorExists;
    }

    public void setVideoProjectorExists(boolean videoProjectorExists) {
        this.videoProjectorExists = videoProjectorExists;
    }

    public boolean isVideoProjectorExists() {
        return videoProjectorExists;
    }

    @Override
    public String toString() {
        return "LectureHall{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", videoProjectorExists=" + videoProjectorExists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LectureHall that = (LectureHall) o;
        return videoProjectorExists == that.videoProjectorExists;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), videoProjectorExists);
    }
}
