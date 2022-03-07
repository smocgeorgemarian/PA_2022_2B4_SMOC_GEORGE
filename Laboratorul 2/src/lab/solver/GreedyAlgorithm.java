package lab.solver;

import lab.model.ComputerLab;
import lab.model.Eveniment;
import lab.model.LectureHall;
import lab.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * <code>Class</code> used to solve a <code>Problem</code> using greedy manner
 * The state of the class is represented by the Problem itself, which is manipulated so it can produce a
 * <code>Solution</code> object.
 * Extends <code>Algorithm</code>
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public class GreedyAlgorithm implements Algorithm {
    Problem pb;
    /**
     * Constructor receiveing a <code>Problem</code> object as a parameter.
     * @param pb the problem to be set up and solved.
     */
    public GreedyAlgorithm(Problem pb) {
        this.pb = pb;
    }
    /**
     * Check if the event can be held in the room.
     * @param event <code>Eveniment</code> object representing the event which is tried to be hold
     * @param room <code>Room</code> object representing the room in which the holding of the event is tried
     * @return <code>true</code> if event can be held
     * <p>
     * <code>false</code> otherwise
     */
    private boolean eventMatchesRoom(Eveniment event, Room room) {
        if (event.getName().contains("C")) {
            return (room instanceof LectureHall) && room.getCapacity() >= event.getSize();
        }
        return (room instanceof ComputerLab) && room.getCapacity() >= event.getSize();
    }
    /**
     * Initialises a <code>List</code> of lists of <code>TimeInterval</code> objects and returns it.
     * @param size The size of the <code>list</code> to be created.
     * @return A <code>List</code> of lists of <code>TimeInterval</code> that is to be manipulated.
     */
    private List<List<TimeInterval>> getInitialTimeIntervals(int size) {
        List<List<TimeInterval>> timeIntervals = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<TimeInterval> roomTimeIntervals = new ArrayList<>();
            timeIntervals.add(roomTimeIntervals);
        }
        return timeIntervals;
    }

    /**
     * Checks if an Event represented by its time interval can be placed in the a room
     * represented by some time intervals
     * @param timeIntervals the List of intervals in which the check is done
     * @param eTimeInterval the interval to be checked if it can be added
     * @return <code>true</code> if it can be added
     * <p>
     * <code>false</code> otherwise
     */
    private boolean canBePlacedOnRoom(List<TimeInterval> timeIntervals, TimeInterval eTimeInterval) {
        for (TimeInterval rTimeInterval : timeIntervals) {
            if (eTimeInterval.intersects(rTimeInterval))
                return false;
        }
        return true;
    }

    /**
     * Solves the <code>Problem</code> and returns a solution using greedy manner
     * @return <code>Solution</code> object representing the assignment of each event to a specific room (if possible)
     */
    public Solution solve() {
        pb.sortEventsByEndTime();
        pb.sortRoomsByCapacity();

        List<Room> rooms = pb.getRooms();
        List<Eveniment> events = pb.getEvents();
        List<List<TimeInterval>> timeIntervals = getInitialTimeIntervals(rooms.size());
        Room[] assignment = new Room[events.size()];
        Solution solution = new Solution(assignment);

        for (int i = 0; i < events.size(); i++) {
            Eveniment event = events.get(i);
            TimeInterval eTimeInterval = new TimeInterval(event.getStartTime(), event.getEndTime());
            int bestDelta = 100000;
            int bestRoomIndex = -1;
            for (int j = 0; j < timeIntervals.size(); j++)
                if (eventMatchesRoom(event, rooms.get(j))) {
                    boolean canBePlaced = canBePlacedOnRoom(timeIntervals.get(j), eTimeInterval);
                    if (canBePlaced) {
                        int delta = rooms.get(j).getCapacity() - event.getSize();
                        if (delta < bestDelta) {
                            bestDelta = delta;
                            bestRoomIndex = j;
                        }
                    }
                }
            if (bestRoomIndex != -1) {
                timeIntervals.get(bestRoomIndex).add(eTimeInterval);
                solution.setAssignmentWithIndex(i, rooms.get(bestRoomIndex));
            }
        }
        return solution;
    }
}
