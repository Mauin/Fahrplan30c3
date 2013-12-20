package com.mtrstudios.Fahrplan30c3.Data;

import android.text.TextUtils;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;

import java.util.*;

/**
 * This class wraps a {@link com.mtrstudios.Fahrplan30c3.Data.Schedule} providing custom access methods
 * Created by markus on 15.12.13.
 */
public class Fahrplan {
    private final Schedule schedule;

    private final List<Event> events = new LinkedList<Event>();
    private final List<String> tracks = new ArrayList<String>();

    public Fahrplan(Schedule schedule) {
        this.schedule = schedule;
        init(schedule);
    }

    private void init(Schedule schedule) {
        if (schedule != null) {
            for (Day day : schedule.getDays()) {
                for (Room room : day.getRooms()) {
                    final List<Event> roomEvents = room.getEvents();
                    events.addAll(roomEvents);
                    for (Event e : roomEvents) {
                        if (!TextUtils.isEmpty(e.getTrack()) && !tracks.contains(e.getTrack())) {
                            tracks.add(e.getTrack());
                        }
                    }
                }
            }
            Collections.sort(tracks);
        }
    }

    public float getVersion() {
        return schedule == null ? 0f : schedule.getVersion();
    }

    public List<String> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    public Collection<Event> findEventsByTrack(final String track) {
        return Collections2.filter(events, new Predicate<Event>() {
            @Override
            public boolean apply(Event event) {
                return track.equals(event.getTrack());
            }
        });
    }

    public Optional<Event> getEvent(final int id) {
        return Iterables.tryFind(events, new Predicate<Event>() {
            @Override
            public boolean apply(Event event) {
                return event.getId() == id;
            }
        });
    }

    public List<Event> getEvents() {
        return events;
    }
}
