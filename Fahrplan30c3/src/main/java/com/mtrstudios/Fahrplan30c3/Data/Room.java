package com.mtrstudios.Fahrplan30c3.Data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Marvin on 12.12.13.
 */
@Root(name="room")
public class Room {
    @Attribute(name="name")
    private String name;

    @ElementList(name="event", inline=true)
    private List<Event> events;

    public String getName() {
        return name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
