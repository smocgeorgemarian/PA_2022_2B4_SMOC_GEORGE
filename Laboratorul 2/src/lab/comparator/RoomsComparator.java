package lab.comparator;

import lab.model.Room;

import java.util.Comparator;
/**
 * Class that implements <code>interface Comparator</code>.
 * Compares 2 <code>Room</code> objects based on their capacity.
 * <p>
 * Used to sort <code>Room</code> objects in ascending order.
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 * @see Comparator
 */
public class RoomsComparator implements Comparator<Room>{
    /**
     * @param o1 First object involved in comparison
     * @param o2 Second object involved in comparison
     * @return &#60;0 in case first object has smaller capacity
     * <p>
     * &#62;0 in case second object has smaller capacity
     * <p>
     * 0 otherwise
     */
    @Override
    public int compare(Room o1, Room o2) {
        return o1.getCapacity() - o2.getCapacity();
    }
}
