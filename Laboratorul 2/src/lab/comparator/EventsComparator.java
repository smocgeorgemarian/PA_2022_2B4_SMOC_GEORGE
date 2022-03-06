package lab.comparator;

import lab.model.Eveniment;

import java.util.Comparator;
/**
 * Class that implements <code>interface Comparator</code>.
 * Compares 2 <code>Eveniment</code> objects based on their endTime.
 * <p>
 * Used to sort <code>Eventiment</code> objects in ascending order.
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 * @see Comparator
 */
public class EventsComparator implements Comparator<Eveniment>{
    /**
     * @param o1 First <code>Eveniment</code> object involved in comparison
     * @param o2 Second <code>Eveniment</code> object involved in comparison
     * @return &#60;0 in case first object has smaller endTime in terms of <code>Date</code> comparison
     * <p>
     * &#62;0 in case second object has smaller endTime in terms of <code>Date</code> comparison
     * <p>
     * 0 otherwise
     */
    @Override
    public int compare(Eveniment o1, Eveniment o2) {
        return o1.getEndTime().compareTo(o2.getEndTime());
    }
}
