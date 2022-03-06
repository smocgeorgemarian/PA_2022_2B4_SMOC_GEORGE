package lab.solver;

import lab.comparator.NodesComparator;
import lab.model.ComputerLab;
import lab.model.Eveniment;
import lab.model.LectureHall;
import lab.model.Room;

import java.util.*;

public class DSaturAlgorithm implements Algorithm {
    private final Problem pb;

    public DSaturAlgorithm(Problem pb) {
        this.pb = pb;
    }

    private boolean areConnected(Eveniment event1, Eveniment event2) {
        if (event1.equals(event2)) {
            return false;
        } else {
            TimeInterval timeInterval1 = new TimeInterval(event1.getStartTime(), event1.getEndTime());
            TimeInterval timeInterval2 = new TimeInterval(event2.getStartTime(), event2.getEndTime());
            return timeInterval1.intersects(timeInterval2);
        }
    }

    private List<List<Integer>> initializeAdjacencyList(List<Eveniment> events) {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for(int i = 0; i < events.size(); ++i) {
            Eveniment event1 = events.get(i);
            List<Integer> neighbours = new ArrayList<>();

            for(int j = 0; j < events.size(); ++j) {
                Eveniment event2 = events.get(j);
                if (this.areConnected(event1, event2)) {
                    neighbours.add(j);
                }
            }
            adjacencyList.add(neighbours);
        }

        return adjacencyList;
    }

    private void setUsed(List<Integer> neighbours, int[] color, boolean[] used, boolean toBeSet) {

        for (int neighbour : neighbours) {
            if (color[neighbour] != -1) {
                used[color[neighbour]] = toBeSet;
            }
        }
    }

    private boolean eventMatchesRoom(Eveniment event, Room room) {
        if (event.getName().contains("C")) {
            return room instanceof LectureHall && room.getCapacity() >= event.getSize();
        } else {
            return room instanceof ComputerLab && room.getCapacity() >= event.getSize();
        }
    }

    private int getUnusedColorForEvent(Eveniment event, boolean[] used, List<Room> rooms) {
        for(int unusedColor = 0; unusedColor < rooms.size(); ++unusedColor) {
            Room room = rooms.get(unusedColor);
            if (!used[unusedColor] && this.eventMatchesRoom(event, room)) {
                return unusedColor;
            }
        }

        return -1;
    }

    public Solution solve() {
        this.pb.sortRoomsByCapacity();
        List<Eveniment> events = this.pb.getEvents();
        List<Room> rooms = this.pb.getRooms();
        List<List<Integer>> adjacencyList = this.initializeAdjacencyList(events);
        List<Set<Integer>> adjacentColors = new ArrayList<>();
        PriorityQueue<NodeInfo> nodesToBeProcessed = new PriorityQueue<>(events.size(), new NodesComparator());
        int[] color = new int[events.size()];
        int[] degree = new int[events.size()];
        boolean[] used = new boolean[rooms.size()];

        int i;
        for(i = 0; i < color.length; ++i) {
            color[i] = -1;
        }

        for(i = 0; i < events.size(); ++i) {
            degree[i] = adjacencyList.get(i).size();
            Set<Integer> colors = new HashSet<>();
            adjacentColors.add(colors);
            NodeInfo nodeInfo = new NodeInfo(0, degree[i], i);
            nodesToBeProcessed.add(nodeInfo);
        }

        while(!nodesToBeProcessed.isEmpty()) {
            NodeInfo nodeInfo = nodesToBeProcessed.poll();
            i = nodeInfo.index();
            this.setUsed(adjacencyList.get(i), color, used, true);
            int unusedColor = this.getUnusedColorForEvent(events.get(i), used, rooms);
            this.setUsed(adjacencyList.get(i), color, used, false);
            color[i] = unusedColor;

            for (int neighbour : adjacencyList.get(i)) {
                if (color[neighbour] == -1) {
                    int size = adjacentColors.get(neighbour).size();
                    NodeInfo nodeInfo1 = new NodeInfo(size, degree[neighbour], neighbour);
                    nodesToBeProcessed.remove(nodeInfo1);
                    adjacentColors.get(neighbour).add(unusedColor);
                    degree[neighbour]--;
                    NodeInfo nodeInfo2 = new NodeInfo(size, degree[neighbour], neighbour);
                    nodesToBeProcessed.add(nodeInfo2);
                }
            }
        }

        Solution solution = new Solution(color.length);
        for(i = 0; i < color.length; ++i) {
            solution.setAssignmentWithIndex(i, rooms.get(color[i]));
        }
        return solution;
    }
}
