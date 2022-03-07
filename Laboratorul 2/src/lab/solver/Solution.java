package lab.solver;

import lab.model.Room;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class containting the representation of an event, encapsulating state information needed
 * for operations done in choosing the best <code>Eventiment</code> for the best <code>Room</code>.
 * <p>
 * The state information includes:
 * <ul>
 * <li> assignment of each <code>Eventiment</code> to a <code>Room</code> if possible
 * </ul>
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public class Solution {
    private Room[] assignment;
    private final Map<Room, Boolean> usedRooms = new HashMap<>();
    /**
     * Constructor that sets up only size of the assignment.
     * @param size The size to be set.
     * @see Solution#Solution(int)  Solution(int)
     */
    public Solution(int size) {
        assignment = new Room[size];
    }
    /**
     * Constructor that assigns specific initial configuration to this assignment.
     * @param assignment The specific initial configuration to be set.
     * @see Room
     * @see Solution#Solution(Room[])  Solution(Room[])
     */
    public Solution(Room[] assignment) {
        this.assignment = assignment;
    }
    /**
     * Sets this assignment to assignment value and adds rooms from assignment in usedRooms
     * @param assignment The value to be set
     * @see Room
     */
    public void setAssignment(Room[] assignment) {
        this.assignment = assignment;
        for (Room room : assignment) {
            usedRooms.put(room, true);
        }
    }
    /**
     * Sets <code>assignment[index]</code> to room and adds room in usedRooms
     * @param index The position in the <code>array</code>.
     * @param room The room to be set on the position index.
     * @see Room
     */
    public void setAssignmentWithIndex(int index, Room room) {
        assignment[index] = room;
        usedRooms.put(room, true);
    }
    /**
     * Returns this assignment representing the configuration in the actual moment for the solution.
     * @return The<code>array</code> assignment.
     * @see Room
     */
    public Room[] getAssignment() {
        return assignment;
    }
    /**
     * Returns the number of <code>Eveniment</code> object that were matched with a <code</code> Room object.
     * @return the number of events that were matched with a room
     */
    public int getNumberOfMatches() {
        int numberOfMatches = 0;
        for (Room room : assignment)
            if (room != null)
                numberOfMatches++;
        return numberOfMatches;
    }
    /**
     *  Returns number of distinct <code>Room</code> objects used by algoritm for which this is folution.
     * @return size of map usedRooms
     */
    public int getNumberOfUsedRooms() {
        return usedRooms.size();
    }
    /**
     * Returns the JSON representation of this <code>Solution</code> object.
     * @return a <code>String</code> containing the JSON representation.
     */
    @Override
    public String toString() {
        return "Solution{" +
                "assignment=" + Arrays.toString(assignment) +
                '}';
    }
}
