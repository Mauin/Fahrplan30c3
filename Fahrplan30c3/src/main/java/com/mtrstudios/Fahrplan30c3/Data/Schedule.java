package com.mtrstudios.Fahrplan30c3.Data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Marvin on 12.12.13.
 */

@Root(name="schedule")
public class Schedule {
    @Element(name="version")
    private float version;

    @Element(name="conference")
    private Conference conference;

    @ElementList(name="day", inline=true)
    private List<Day> days;

    public float getVersion() {
        return version;
    }

    public Conference getConference() {
        return conference;
    }

    public List<Day> getDays() {
        return days;
    }
}
