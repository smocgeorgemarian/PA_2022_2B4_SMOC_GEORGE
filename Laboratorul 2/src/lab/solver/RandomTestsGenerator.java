package lab.solver;

import lab.model.ComputerLab;
import lab.model.Eveniment;
import lab.model.LectureHall;
import lab.model.Room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Class containting the data needed to generate random big tests, filling <code>Problem</code> object with
 * <code>Eveniment</code> and <code>Room</code> objects.
 * <p>
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public class RandomTestsGenerator {
    final Random random = new Random();
    static final int MAX_TESTS = 30;
    static final int MIN_TESTS = 3;
    static final int MAX_DAYS = 30;
    static final int MIN_DAYS = 1;
    static final int MAX_HOURS = 20;
    static final int MIN_HOURS = 1;
    static final int MIN_EVENTS = 1000;
    static final int MAX_EVENTS = 2000;
    static final int MIN_ROOMS = 1000;
    static final int MAX_ROOMS = 2000;
    static final int MAX_CAPACITY = 100;
    static final int MIN_CAPACITY = 40;
    static final String[] operatingSystems = {"MacOS", "Linux", "Windows"};
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy H");
    /**
     * Returns a number of events between MIN_EVENTS AND MAX_EVENTS + MIN_EVENTS - 1
     * @return number of events generated randomly
     */
    public int generateNumberOfEvents() {
        return random.nextInt(MAX_EVENTS) + MIN_EVENTS;
    }
    /**
     * Returns a number of rooms between MIN_ROOMS AND MAX_ROOMS + MIN_ROOMS - 1
     * @return number of rooms generated randomly
     */
    public int generateNumberOfRooms() {
        return random.nextInt(MAX_ROOMS) + MIN_ROOMS;
    }
    /**
     * Returns a number of tests between MIN_TESTS AND MAX_TESTS + MIN_TESTS - 1
     * @return number of tests generated randomly
     */
    public int generateNumberOfTests() {
        return random.nextInt(MAX_TESTS) + MIN_TESTS;
    }
    /**
     * Fills <code>Problem</code> object with numberOfEvents <code>Eveniment</code> objects
     * @param pb <code>Problem</code> object to be filled
     * @param numberOfEvents number of events to be filled, generated randomly
     */
    public void setEvents(Problem pb, int numberOfEvents) {
        List<Eveniment> events = new ArrayList<>();
        int numberOfCourses = 0;
        int numberOfLabs = 0;
        for (int eventIndex = 0; eventIndex < numberOfEvents; eventIndex++) {
            int randomDay = random.nextInt(MAX_DAYS) + MIN_DAYS;
            int randomStartHour = random.nextInt(MAX_HOURS) + MIN_HOURS;

            LocalDateTime startTime = LocalDateTime.parse(randomDay + "/02/2022 " + randomStartHour, formatter);
            LocalDateTime endTime = LocalDateTime.parse(randomDay + "/02/2022 " + (randomStartHour + 2), formatter);
            boolean isLecture = random.nextBoolean();
            String eventName;
            if (isLecture) {
                ++numberOfCourses;
                eventName = "C" + numberOfCourses;
            } else {
                ++numberOfLabs;
                eventName = "L" + numberOfLabs;
            }
            int randomCapacity = random.nextInt(MAX_CAPACITY) + MIN_CAPACITY;
            events.add(new Eveniment(eventName, randomCapacity, startTime, endTime));
        }
        pb.setEvents(events);
    }
    /**
     * Fills <code>Problem</code> object with numberOfRooms <code>Room</code> objects
     * @param pb <code>Problem</code> object to be filled
     * @param numberOfRooms number of rooms to be filled, generated randomly
     */
    public void setRooms(Problem pb, int numberOfRooms) {
        List<Room> rooms = new ArrayList<>();
        for (int roomIndex = 0; roomIndex < numberOfRooms; roomIndex++) {
            String randomRoomNumber = "" + (random.nextInt(numberOfRooms) + 1);
            int randomCapacity = random.nextInt(MAX_CAPACITY) + MIN_CAPACITY;
            boolean isComputerLab = random.nextBoolean();
            if (isComputerLab) {
                String randomOS = operatingSystems[random.nextInt(operatingSystems.length)];
                rooms.add(new ComputerLab(randomRoomNumber, randomCapacity, randomOS));
            }
            else {
                boolean randomVideoProjectorExists = random.nextBoolean();
                rooms.add(new LectureHall(randomRoomNumber, randomCapacity, randomVideoProjectorExists));
            }
        }
        pb.setRooms(rooms);
    }
}
