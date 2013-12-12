package com.mtrstudios.Fahrplan30c3.Data;

import org.simpleframework.xml.Element;

/**
 * Created by Marvin on 12.12.13.
 */
public class Conference {
    @Element
    private String acronym;

    @Element
    private String title;

    @Element
    private String start;

    @Element
    private String end;

    @Element
    private int days;

    @Element
    private String timeslot_duration;

    public String getAcronym() {
        return acronym;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getDays() {
        return days;
    }

    public String getTimeslot_duration() {
        return timeslot_duration;
    }
}
