//package com.company.homework;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Main2 {
//    public static void createEvents(Problem problem) {
//        List<Eveniment> events = new ArrayList<>();
//        events.add(new Eveniment("C1", 100, 8, 10));
//        events.add(new Eveniment("C2", 100, 10, 12));
//
//        events.add(new Eveniment("L1", 30, 8, 10));
//        events.add(new Eveniment("L2", 30, 8, 10));
//        events.add(new Eveniment("L3", 30, 10, 12));
//        problem.setEvents(events);
//    }
//
//    public static void createRooms(Problem problem) {
//        List<Room> rooms = new ArrayList<>();
//        rooms.add(new ComputerLab("401", 30, "MacOS"));
//        rooms.add(new ComputerLab("403", 30, "Linux"));
//        rooms.add(new ComputerLab("405", 30, "Windows"));
//        rooms.add(new LectureHall("309", 100, true));
//        problem.setRooms(rooms);
//    }
//
//    public static void main(String[] args) {
//        Problem pb = new Problem();
//        createEvents(pb);
//        createRooms(pb);
//        System.out.println(pb);
//    }
//}
