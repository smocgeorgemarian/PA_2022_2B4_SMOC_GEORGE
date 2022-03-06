package lab.app;

import lab.model.ComputerLab;
import lab.model.Eveniment;
import lab.model.LectureHall;
import lab.model.Room;
import lab.solver.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Class used to show the example presented at the 2nd Laboratory and the random tests for Bonus part(to be implemented).
 * @author George Smoc
 * @version 1.0
 * @since 2022-03-06
 */
public class Main {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy H");
    static final String DATE_8_HOUR = "20/02/2022 8";
    static final String DATE_10_HOUR = "20/02/2022 10";
    static final String DATE_12_HOUR = "20/02/2022 12";
    /**
     * Creates Events from example for which a solution will be generated using greedy manner.
     * <p>
     * The association between Events and Rooms will be shown in JSON format.
     * @param problem <code>Problem</code> object to be filled with <code>Eveniment</code> objects.
     * @see Problem
     */
    public static void createEveniments(Problem problem) {
        List<Eveniment> events = new ArrayList<>();
        LocalDateTime startTime;
        LocalDateTime endTime;

        startTime = LocalDateTime.parse(DATE_8_HOUR, formatter);
        endTime = LocalDateTime.parse(DATE_10_HOUR, formatter);
        events.add(new Eveniment("C1", 100, startTime, endTime));

        startTime = LocalDateTime.parse(DATE_10_HOUR, formatter);
        endTime = LocalDateTime.parse(DATE_12_HOUR, formatter);
        events.add(new Eveniment("C2", 100, startTime, endTime));

        startTime = LocalDateTime.parse(DATE_8_HOUR, formatter);
        endTime = LocalDateTime.parse(DATE_10_HOUR, formatter);
        events.add(new Eveniment("L1", 30, startTime, endTime));

        startTime = LocalDateTime.parse(DATE_8_HOUR, formatter);
        endTime = LocalDateTime.parse(DATE_10_HOUR, formatter);
        events.add(new Eveniment("L2", 30, startTime, endTime));

        startTime = LocalDateTime.parse(DATE_10_HOUR, formatter);
        endTime = LocalDateTime.parse(DATE_12_HOUR, formatter);
        events.add(new Eveniment("L3", 30, startTime, endTime));
        problem.setEvents(events);
    }
    /**
     * Creates Rooms from example for which a solution will be generated using greedy manner.
     * @param problem <code>Problem</code> object to be filled with <code>Room</code> objects.
     * @see Problem
     */
    public static void createRooms(Problem problem) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new ComputerLab("401", 30, "MacOS"));
        rooms.add(new ComputerLab("403", 30, "Linux"));
        rooms.add(new ComputerLab("405", 30, "Windows"));
        rooms.add(new LectureHall("309", 100, true));
        problem.setRooms(rooms);
    }
    /**
     * Tests the application on the example from the 2nd Laboratory.
     */
    private static void homework() {
        Problem pb = new Problem();
        createEveniments(pb);
        createRooms(pb);
        System.out.println(pb);
        Algorithm greedy = new GreedyAlgorithm(pb);
        Solution sol = greedy.solve();
        System.out.println("Solution found by greedy:\n" + sol);

        Algorithm dSatur = new DSaturAlgorithm(pb);
        sol = dSatur.solve();
        System.out.println("Solution found by DSatur:\n" + sol);
    }
    /**
     * Tests the application for homework and bonus(to be implemented) parts.
     * @param args arguments of the program - to be ignored
     */
    public static void main(String[] args) {
        homework();
    }
}
