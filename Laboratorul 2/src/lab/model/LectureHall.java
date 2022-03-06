package lab.model;

import java.util.Objects;
/**
 * Class containing the representation of the Lecture Rooms.
 * Keeps state information that allow finding the best <code>LectureHall</code> for an <code>Eveniment</code>
 * <p>
 * Extends abstract <code>class Room</code>.
 * <p>
 * State information contains:
 * <ul>
 * <li> All information from Room
 * <li> flag that says if videoprojector exists
 * </ul>
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public class LectureHall extends Room {
    private boolean videoProjectorExists;
    /**
     * Constructor that contains name, capacity and videoProjectorExists.
     * @param name name of the Room
     * @param capacity number of seats for students
     * @param videoProjectorExists flag that says if there is a videoprojector in the room
     * @see Room
     */
    public LectureHall(String name, int capacity, boolean videoProjectorExists) {
        this.name = name;
        this.capacity = capacity;
        this.videoProjectorExists = videoProjectorExists;
    }

    /**
     * Sets this videoProjectorExists to the value videoProjectorExists.
     * @param videoProjectorExists value to be set for flag this videoProjectorExists
     */
    public void setVideoProjectorExists(boolean videoProjectorExists) {
        this.videoProjectorExists = videoProjectorExists;
    }

    /**
     * Returns the flag that says if videoprojector exists.
     * @return <code>true</code> if videoprojector exists
     * <p>
     * <code>false</code> otherwise
     */
    public boolean isVideoProjectorExists() {
        return videoProjectorExists;
    }
    /**
     * Returns the JSON representation of this <code>LectureHall</code> object.
     * @return a <code>String</code> containing the JSON representation
     */
    @Override
    public String toString() {
        return "LectureHall{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", videoProjectorExists=" + videoProjectorExists +
                '}';
    }

    /**
     * Checks if an objects is equal to this <code>LectureHall</code> object.
     * @param o The object with which this object is compared
     * @return <code>true</code> if objects are equal
     * <p>
     * <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LectureHall that = (LectureHall) o;
        return videoProjectorExists == that.videoProjectorExists;
    }
    /**
     * Computes hashCode of the object.
     * <p>
     * Note: If two objects are the equal have the same hashCode, the mutual affirmation is not always <code>true</code>.
     * @return <code>int</code> value representing the Hash Code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), videoProjectorExists);
    }
}
