package com.mtrstudios.Fahrplan30c3.Data;

/**
 * This class wraps a {@link com.mtrstudios.Fahrplan30c3.Data.Schedule} providing custom access methods
 * Created by markus on 15.12.13.
 */
public class Fahrplan {
    private final Schedule schedule;

    public Fahrplan(Schedule schedule) {
        this.schedule = schedule;
    }

    public float getVersion() {
        return schedule.getVersion();
    }
}
