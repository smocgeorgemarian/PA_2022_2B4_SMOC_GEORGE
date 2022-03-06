package lab.model;

import java.util.Objects;

/**
 * Class containing the representation of the Computer Laboratories.
 * Keeps state information that allow finding the best <code>ComputerLab</code> for an <code>Eveniment</code>
 * <p>
 * Extends abstract <code>class Room</code>.
 * <p>
 * State information contains:
 * <ul>
 * <li> All information from Room
 * <li> Operating System
 * </ul>
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 * @see Room
 */
public class ComputerLab extends Room {
    private String operatingSystem;
    /**
     * Constructor setting up name, capacity and operatingSystem.
     * @param name Name of the Laboratory Room
     * @param capacity Number of students that can maximum attend the Laboratory
     * @param operatingSystem Operating System used on computers in the room
     */
    public ComputerLab(String name, int capacity, String operatingSystem) {
        this.name = name;
        this.capacity = capacity;
        this.operatingSystem = operatingSystem;
    }
    /**
     * Sets operatingSystem.
     * @param operatingSystem <code>String</code> containing the Operating System to be set
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
    /**
     * Returns a <code>String</code> containing this Operating System.
     * @return <code>String</code> object containing the name of the Operating System
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }
    /**
     * @return <code>String</code> object containing the JSON representation of this <code>ComputerLab</code>
     */
    @Override
    public String toString() {
        return "ComputerLab{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", operatingSystem='" + operatingSystem + '\'' +
                '}';
    }
    /**
     * Checks if <code>Object</code> o is equal to this <code>ComputerLab</code>.
     * @param o <code>Object</code> to be compared with this <code>ComputerLab</code>.
     * @return boolean value: <code>true</code> if <code>Object</code> o is equal to this <code>ComputerLab</code>
     * <p>
     * <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComputerLab that = (ComputerLab) o;
        return Objects.equals(operatingSystem, that.operatingSystem);
    }
    /**
     * Computes hashCode of the object.
     * <p>
     * Note: If two objects are the equal have the same hashCode, the mutual affirmation is not always <code>true</code>.
     * @return <code>int</code> value representing the Hash Code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), operatingSystem);
    }
}
