package lab.solver;

import lab.comparator.NodesComparator;
import lab.comparator.RoomsComparator;
import lab.model.ComputerLab;
import lab.model.Eveniment;
import lab.model.LectureHall;
import lab.model.Room;

import java.util.*;
/**
 * Class containting state information needed to solve solution via DSatur Algorithm, filling <code>Solution</code>
 * object with <code>Room</code> objects suitable for every <code>Eveniment</code> object in the <code>Problem</code>
 * object.
 * <p>
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public class DSaturAlgorithm implements Algorithm {
    private final Problem pb;

    /**
     * Constructor that fills <code>Problem</code> object from class, needed for processing the events and the rooms.
     * @param pb <code>Problem</code> object that fills this pb
     */
    public DSaturAlgorithm(Problem pb) {
        this.pb = pb;
    }
    /**
     * Checks if two events are Connected, meaning they cannot occour in the same room, as their time intervals intersect.
     * @param event1 the first <code>Eveniment</code> object
     * @param event2 the second <code>Eveniment</code> object
     * @return <code>true</code> if their time intervals intersect
     * <p>
     * <code>false</code> otherwise
     */
    private boolean areConnected(Eveniment event1, Eveniment event2) {
        if (event1.equals(event2)) {
            return false;
        } else {
            TimeInterval timeInterval1 = new TimeInterval(event1.getStartTime(), event1.getEndTime());
            TimeInterval timeInterval2 = new TimeInterval(event2.getStartTime(), event2.getEndTime());
            return timeInterval1.intersects(timeInterval2);
        }
    }
    /**
     * Initializes the adjacency <code>List</code> of events, needed for modelling <code>Problem</code> as a <code>
     * Graph</code>.
     * @param events Events that become verticles in <code>Graph</code>, based on their time intervals intersections
     * @return the adjacency <code>List</code> of events
     */
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
    /**
     * Sets the color used by the neighbours of a node as unusable/usable, so the color chosen is not one used by a neighbour
     * at any time.
     * @param neighbours the <code>List</code> of the neighbours of the node
     * @param color the <code>array</code> of colors of the nodes, array[node] = color of the node
     * @param used the <code>array</code> of booleans marking colors as unusable/usable by current node
     * @param toBeSet the value to be set on used <code</code> array
     */
    private void setUsed(List<Integer> neighbours, int[] color, boolean[] used, boolean toBeSet) {

        for (int neighbour : neighbours) {
            if (color[neighbour] != -1) {
                used[color[neighbour]] = toBeSet;
            }
        }
    }
    /**
     * Check if an <code>Eveniment</code> object can be associated with a <code>Room</code> object.
     * @param event the <code>Eveniment</code> object
     * @param room the <code>Room</code> object
     * @return <code>true</code> if they can be associated
     * <p>
     * <code>false</code> otherwise
     */
    private boolean eventMatchesRoom(Eveniment event, Room room) {
        if (event.getName().contains("C")) {
            return room instanceof LectureHall && room.getCapacity() >= event.getSize();
        } else {
            return room instanceof ComputerLab && room.getCapacity() >= event.getSize();
        }
    }
    /**
     * Returns the first color that can be used for current node, repecting matching conditions.
     * @param event <code>Eveniment</code> object to be matched
     * @param used the <code>array</code> telling whether the color is usable or not
     * @param rooms the <code>List</code> of rooms from which one can be chosen
     * @return the index of the room (color) that will be used or -1 if impossible to find one
     */
    private int getUnusedColorForEvent(Eveniment event, boolean[] used, List<Room> rooms) {
        for(int unusedColor = 0; unusedColor < rooms.size(); ++unusedColor) {
            Room room = rooms.get(unusedColor);
            if (!used[unusedColor] && this.eventMatchesRoom(event, room)) {
                return unusedColor;
            }
        }
        return -1;
    }
    /**
     * Fills the solution with the result of applying the algorithm.
     * @param color the <code>array</code> that contains the final colors of the nodes
     * @param rooms the <code>List</code> of rooms which will be used based on the colors resulted after applying algoritm
     * @return a <code>Solution</code> object that is to be manipulated
     */
    private Solution newFilledSolution(int[] color, List<Room> rooms) {
        Solution solution = new Solution(color.length);
        for(int i = 0; i < color.length; ++i) {
            solution.setAssignmentWithIndex(i, rooms.get(color[i]));
        }
        return solution;
    }
    /**
     * Solves the problem via applying DSatur Algorithm, after modelling the problem as a <code>Graph</code>.
     * @return a <code>Solution</code> object that is to be manipulated
     */
    public Solution solve() {
        List<Eveniment> events = this.pb.getEvents();
        List<Room> rooms = this.pb.getRooms();
        rooms.sort(new RoomsComparator());

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
        return newFilledSolution(color, rooms);
    }
}
