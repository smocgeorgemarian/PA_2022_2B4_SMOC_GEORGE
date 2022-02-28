package com.company.homework;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private List<Eveniment> events = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
//    public void sortEventsByEndTime() {
//        events.sort();
//    }

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

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Eveniment> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "events=" + events +
                ", rooms=" + rooms +
                '}';
    }
}
