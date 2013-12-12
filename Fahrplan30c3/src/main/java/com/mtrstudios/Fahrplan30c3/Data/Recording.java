package com.mtrstudios.Fahrplan30c3.Data;

import org.simpleframework.xml.Element;

/**
 * Created by Marvin on 12.12.13.
 */
public class Recording {
    @Element(required=false)
    private String license;

    @Element
    private boolean optout;
}
