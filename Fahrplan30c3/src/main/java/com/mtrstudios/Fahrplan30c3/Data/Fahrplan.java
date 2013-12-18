package com.mtrstudios.Fahrplan30c3.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class wraps a {@link com.mtrstudios.Fahrplan30c3.Data.Schedule} providing custom access methods
 * Created by markus on 15.12.13.
 */
public class Fahrplan {
    private final Schedule schedule;

    public Fahrplan(Schedule schedule) {
        this.schedule = schedule;
    }

    public float getVersion() {
        return schedule.getVersion();
    }

    /**
     * FIXME: Rather useless dummy method for list view testing. REMOVE!!!!
     *
     * @return
     */
    public List<Event> getAllEvents() {
        final ArrayList<Event> events = new ArrayList<Event>();
        if (schedule != null) {
            for (Day day : schedule.getDays()) {
                for (Room room : day.getRooms()) {
                    events.addAll(room.getEvents());
                }
            }
        }
        return events;
    }

    /**
     * TODO - Dummy for Detail View Testing.
     * @param id
     * @return
     */
    public Event getEventById(int id) {
        List<Event> events = getAllEvents();
        for (Event event : events) {
            if (id == event.getId()) {
                return event;
            }
        }
        return null;
    }
}
