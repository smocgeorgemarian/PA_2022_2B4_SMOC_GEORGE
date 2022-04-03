package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class GridManager {
    private final Set<Stick> stickSet = new HashSet<>();
    public void addStick(Stick stick) {
        this.stickSet.add(stick);
    }

    public void removeStick(Stick stick) {
        this.stickSet.remove(stick);
    }

    public Set<Stick> getStickSet() {
        return stickSet;
    }
}
