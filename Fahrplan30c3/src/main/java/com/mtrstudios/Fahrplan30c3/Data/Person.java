package com.mtrstudios.Fahrplan30c3.Data;

import com.google.common.base.Function;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Marvin on 12.12.13.
 */
public class Person {

    public static final Function<Person,String> NAME_FUNCTION = new Function<Person, String>() {
        @Override
        public String apply(Person person) {
            return person.getName();
        }
    };

    @Attribute
    private int id;

    @Element(name="person", required=false)
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
