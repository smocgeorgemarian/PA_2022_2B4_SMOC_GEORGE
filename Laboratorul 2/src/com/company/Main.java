package com.company;

import com.company.compulsory.Eveniment;
import com.company.compulsory.Problem;
import com.company.compulsory.Room;
import com.company.compulsory.RoomType;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void createEvenimentsCompulsory(Problem problem) {
        List<Eveniment> Eveniments = new ArrayList<>();
        Eveniments.add(new Eveniment("C1", 100, 8, 10));
        Eveniments.add(new Eveniment("C2", 100, 10, 12));

        Eveniments.add(new Eveniment("L1", 30, 8, 10));
        Eveniments.add(new Eveniment("L2", 30, 8, 10));
        Eveniments.add(new Eveniment("L3", 30, 10, 12));
        problem.setEvents(Eveniments);
    }

    public static void createRoomsCompulsory(Problem problem) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("401", 30, RoomType.COMPUTER_LAB));
        rooms.add(new Room("403", 30, RoomType.COMPUTER_LAB));
        rooms.add(new Room("405", 30, RoomType.COMPUTER_LAB));
        rooms.add(new Room("309", 100, RoomType.LECTURE_HALL));
        problem.setRooms(rooms);
    }

    public static void main(String[] args) {
        Problem pb = new Problem();
        createEvenimentsCompulsory(pb);
        createRoomsCompulsory(pb);
        System.out.println(pb);
    }
}
