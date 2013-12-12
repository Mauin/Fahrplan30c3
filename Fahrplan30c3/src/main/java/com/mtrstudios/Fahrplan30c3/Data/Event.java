package com.mtrstudios.Fahrplan30c3.Data;

import com.android.volley.toolbox.StringRequest;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Marvin on 12.12.13.
 */
@Root(name="event")
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

    @Element(required=false)
    private String subtitle;

    @Element
    private String track;

    @Element(required=false)
    private String type;

    @Element(required=false)
    private String language;

    @Element(name="abstract", required=false)
    private String abstract_description;

    @Element(required=false)
    private String description;

    @ElementList
    private List<Person> persons;

    @ElementList
    private List<Link> links;
}
