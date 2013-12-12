package com.mtrstudios.Fahrplan30c3.Data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Marvin on 12.12.13.
 */
public class Person {
    @Attribute
    private int id;

    @Element(name="person", required=false)
    private String name;

}
