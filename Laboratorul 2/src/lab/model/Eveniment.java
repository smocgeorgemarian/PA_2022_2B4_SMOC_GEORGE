package lab.model;

import java.util.Objects;
import java.time.LocalDateTime;

/**
 * Class containting the representation of an event, encapsulating state information needed
 * for operations done in choosing the best <code>Eventiment</code> for the best <code>Room</code>.
 * <p>
 * The state information includes:
 * <ul>
 * <li> The name of the event
 * <li> The number of the students
 * <li> The start date of the event
 * <li> The end date of the event
 * </ul>
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public class Eveniment {
    private String name;
    private int size;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    /**
     * Constructor setting up the name, the startTime and the endTime.
     * @param name <code>String</code> object representing the name to be set up
     * @param size <code>int</code> representing the size to be set up
     * @param startTime <code>LocalDateTime</code> object representing the startTime to be set up
     * @param endTime <code>LocalDateTime</code> object representing the endTime to be set up
     * @see LocalDateTime
     */
    public Eveniment(String name, int size, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.size = size;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Sets this endTime to specific value.
     * @param endTime <code>LocalDateTime</code> object representing the endTime to be set
     * @see LocalDateTime
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    /**
     * Sets this startTime to specific value.
     * @param startTime <code>LocalDateTime</code> object representing the startTime to be set
     * @see LocalDateTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    /**
     * Sets this size to specific value.
     * @param size to be set
     */
    public void setSize(int size) {
        this.size = size;
    }
    /**
     * Sets this name to specific value.
     * @param name to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns this endTime.
     * @return <code>LocalDateTime</code> representing endTime
     * @see LocalDateTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
    /**
     * Returns this startTime.
     * @return <code>LocalDateTime</code> representing startTime
     * @see LocalDateTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }
    /**
     * Returns this size.
     * @return <code>int</code> representing size
     */
    public int getSize() {
        return size;
    }
    /**
     * Returns this name.
     * @return <code>String</code> object containing name
     */
    public String getName() {
        return name;
    }
    /**
     * Returns a <code>String</code> object containing the state information in JSON format.
     * @return <code>String</code> representing the JSON
     */
    @Override
    public String toString() {
        return "Eveniment{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
    /**
     * Checks if <code>Object</code> o is equal to this <code>Eveniment</code>.
     * @param o <code>Object</code> to be compared with this <code>Eveniment</code>
     * @return boolean value: <code>true</code> if <code>Object</code> o is equal to this <code>Eveniment</code>
     * <p>
     * <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eveniment eveniment = (Eveniment) o;
        return size == eveniment.size && startTime == eveniment.startTime && endTime == eveniment.endTime && Objects.equals(name, eveniment.name);
    }
    /**
     * Computes hashCode of the object.
     * <p>
     * Note: If two objects are the equal have the same hashCode, the mutual affirmation is not always true.
     * @return <code>int</code> value representing the Hash Code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, size, startTime, endTime);
    }
}
