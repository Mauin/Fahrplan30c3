package com.mtrstudios.Fahrplan30c3.Data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Marvin on 12.12.13.
 */
public class Link {
    @Attribute(name="href")
    private String link;

    @Element(name="link", required=false)
    private String description;

}
