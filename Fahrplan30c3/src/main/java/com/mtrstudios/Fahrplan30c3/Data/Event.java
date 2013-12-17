package com.mtrstudios.Fahrplan30c3.Data;

import android.text.TextUtils;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Marvin on 12.12.13.
 */
@Root(name = "event")
public class Event {
    @Attribute
    private String guid;

    @Attribute
    private int id;

    @Element
    private String start;

    @Element
    private String duration;

    @Element
    private String room;

    @Element
    private String slug;

    @Element
    private Recording recording;

    @Element
    private String title;

    @Element(required = false)
    private String subtitle;

    @Element
    private String track;

    @Element(required = false)
    private String type;

    @Element(required = false)
    private String language;

    @Element(name = "abstract", required = false)
    private String abstract_description;

    @Element(required = false)
    private String description;

    @ElementList
    private List<Person> persons;

    @ElementList
    private List<Link> links;
    private int speakerName;

    public String getGuid() {
        return guid;
    }

    public int getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public String getDuration() {
        return duration;
    }

    public String getRoom() {
        return room;
    }

    public String getSlug() {
        return slug;
    }

    public Recording getRecording() {
        return recording;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTrack() {
        return track;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getAbstract_description() {
        return abstract_description;
    }

    public String getDescription() {
        return description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getSpeakerNames() {
        return TextUtils.join(", ", Iterables.filter(Iterables.transform(getPersons(), Person.NAME_FUNCTION), Predicates.notNull()));
    }

}
