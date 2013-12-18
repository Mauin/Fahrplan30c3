package com.mtrstudios.Fahrplan30c3.Data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.lang.annotation.Retention;
import java.util.List;

/**
 * Created by Marvin on 12.12.13.
 */
@Root(name="day", strict = false)
public class Day {

    @Attribute(name="date")
    private String date;

    @Attribute(name="index")
    private int index;

    @ElementList(name="room", inline=true)
    private List<Room> rooms;

    public String getDate() {
        return date;
    }

    public int getIndex() {
        return index;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
