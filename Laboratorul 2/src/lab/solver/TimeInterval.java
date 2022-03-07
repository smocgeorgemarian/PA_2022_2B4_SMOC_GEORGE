package lab.solver;
import java.time.LocalDateTime;
/**
 * <code>TimeInterval Record</code> used to keep start time and end time together and check if a
 * [start time, end time) intersects with another interval.
 * Used to check if two Events can happen in the same Room.
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
record TimeInterval(LocalDateTime startTime, LocalDateTime endTime) {
    /**
     * Checks if 2 intervals intersect.
     * @param timeInterval the timeInterval with witch intersection is checked.
     * @return true if this <code>TimeInterval</code> intersects with timeInterval
     * <p>
     * <code>false</code> otherwise
     */
    public boolean intersects(TimeInterval timeInterval) {
        if (this.startTime.compareTo(timeInterval.startTime) <= 0 ) {
            return (this.endTime.compareTo(timeInterval.startTime) > 0);
        }
        return (timeInterval.endTime.compareTo(this.startTime) > 0);
    }
    /**
     * Returns a <code>String</code> object containing the state information in JSON format.
     * @return <code>String</code> representing the JSON.
     */
    @Override
    public String toString() {
        return "TimeInterval{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}