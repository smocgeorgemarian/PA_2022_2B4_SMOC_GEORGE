package lab.model;

import java.util.Objects;
/**
 * <code>Abstract Class</code> containing the representation of the Rooms.
 * Keeps state information that allow finding the best <code>Room</code> for an <code>Eveniment</code>.
 * <p>
 * State information contains:
 * <ul>
 * <li> Name of the room
 * <li> Capacity of the room
 * </ul>
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public abstract class Room {
    /**
     * name of the Room
     */
    protected String name;
    /**
     * capacity of the Room
     */
    protected int capacity;
    /**
     * Sets this capacity to the value of capacity.
     * @param capacity The value to be set for this capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    /**
     * Sets this name to the value of name.
     * @param name The value to be set for this name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the value of the capacity.
     * @return this capacity
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * Returns the value of the name.
     * @return this name
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the JSON representation of this <code>Room</code> object.
     * @return a <code>String</code> containing the JSON representation
     */
    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
    /**
     * Checks if <code>Object</code> o is equal to this <code>Roon</code>.
     * @param o <code>Object</code> to be compared with this <code>Room</code>
     * @return boolean value: <code>true</code> if <code>Object</code> o is equal to this <code>Room</code>
     * <p>
     * <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return capacity == room.capacity && Objects.equals(name, room.name);
    }
    /**
     * Computes hashCode of the object.
     * <p>
     * Note: If two objects are the equal have the same hashCode, the mutual affirmation is not always true.
     * @return <code>int</code> value representing the Hash Code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, capacity);
    }
}

