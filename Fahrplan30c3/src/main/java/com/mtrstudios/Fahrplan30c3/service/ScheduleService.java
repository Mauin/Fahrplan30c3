package com.mtrstudios.Fahrplan30c3.service;

import android.content.Context;
import com.mtrstudios.Fahrplan30c3.Data.Schedule;

/**
 * Provides access to the schedule
 * Created by markus on 15.12.13.
 */
public interface ScheduleService {

    /**
     * Transparently loads the current schedule from disk
     *
     * @param ctx
     * @return
     */
    Schedule getSchedule(Context ctx) throws Exception;

    void setSchedule(Context ctx, Schedule schedule) throws Exception;
}
