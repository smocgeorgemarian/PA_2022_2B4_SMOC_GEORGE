package lab.solver;

import lab.comparator.EventsComparator;
import lab.comparator.RoomsComparator;
import lab.model.Eveniment;
import lab.model.Room;

import java.util.ArrayList;
import java.util.List;
/**
 * Class containing the representation of the problem, being
 * able to manipulate <code>Event</code> and <code>Room</code> objects, such that the problem
 * can be solved optimally.
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public class Problem {
    private final List<Eveniment> events = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    /**
     * Sorts events in ascending order by their endTime
     * @see Eveniment
     * @see RoomsComparator
     */
    public void sortEventsByEndTime() {
        events.sort(new EventsComparator());
    }
    /**
     * Sorts rooms in ascending order by their capacity
     * @see Room
     * @see RoomsComparator
     */
    public void sortRoomsByCapacity() {
        rooms.sort(new RoomsComparator());
    }
    /**
     * Adds a <code>Room</code> object to the rooms <code>List</code>
     * if it is not already added
     * @param room The room to be added
     * @see Room
     */
    public void addRoom(Room room) {
        boolean foundEqual = false;
        for (Room existingRoom: rooms) {
            if (existingRoom.equals(room)) {
                foundEqual = true;
                break;
            }
        }
        if (!foundEqual)
            rooms.add(room);
    }
    /**
     * Adds a <code>Eveniment</code> object to the events <code>List</code>
     * if it is not already added
     * @param event The event to be added
     * @see Eveniment
     */
    public void addEvent(Eveniment event) {
        boolean foundEqual = false;
        for (Eveniment existingEvent: events) {
            if (existingEvent.equals(event)) {
                foundEqual = true;
                break;
            }
        }
        if (!foundEqual)
            events.add(event);
    }
    /**
     * Sets the rooms <code>List</code> with the value of parameter
     * @param rooms The rooms <code>List</code> to be set
     * @see Room
     */
    public void setRooms(List<Room> rooms) {
        this.rooms.clear();
        for (Room room1: rooms) {
            int matches = 0;
            for (Room room2 : rooms) {
                if (room1.equals(room2))
                    matches++;
                if (matches > 1)
                    break;
            }
            if (matches == 1)
                this.rooms.add(room1);
        }
    }
    /**
     * Sets the events <code>List</code> with the value of parameter
     * @param events The events <code>List</code> to be set
     * @see Eveniment
     */
    public void setEvents(List<Eveniment> events) {
        this.events.clear();
        for (Eveniment event1: events) {
            int matches = 0;
            for (Eveniment event2 : events) {
                if (event1.equals(event2))
                    matches++;
                if (matches > 1)
                    break;
            }
            if (matches == 1)
                this.events.add(event1);
        }
    }
    /**
     * Return the rooms <code>List</code> contained in the Problem to be solved
     * @return A <code>List</code> caintaining <code>Room</code> objects
     * @see Room
     */
    public List<Room> getRooms() {
        return rooms;
    }
    /**
     * Return the events <code>List</code> contained in the Problem to be solved
     * @return A <code>List</code> caintaining <code>Eveniment</code> objects
     * @see Eveniment
     */
    public List<Eveniment> getEvents() {
        return events;
    }
    /**
     * @return <code>String</code> containing the JSON representation of the <code>Problem</code>
     */
    @Override
    public String toString() {
        return "Problem{" +
                "events=" + events +
                ", rooms=" + rooms +
                '}';
    }
}
