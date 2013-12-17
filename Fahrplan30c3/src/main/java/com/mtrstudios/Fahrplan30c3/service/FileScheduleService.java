package com.mtrstudios.Fahrplan30c3.service;

import android.content.Context;
import android.os.Environment;
import com.mtrstudios.Fahrplan30c3.Data.Schedule;
import org.simpleframework.xml.core.Persister;

import java.io.*;

/**
 * This implementation is backed with an xml file stored on disk.
 * <p/>
 * An instance of this service should be created and shared between activities
 * Created by markus on 15.12.13.
 */
public class FileScheduleService implements ScheduleService {
    static final String FILENAME = "schedule.xml";

    private Schedule schedule;

    public Schedule getSchedule(Context ctx) throws Exception {
        if (schedule == null) {
            schedule = read(ctx);
        }
        return schedule;
    }

    @Override
    public void setSchedule(Context ctx, Schedule schedule) throws Exception {
        this.schedule = schedule;
        write(schedule, ctx);
    }

    private void write(Schedule schedule, Context ctx) throws Exception {
        FileOutputStream fos = null;
        try {
            fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            new Persister().write(schedule, fos, "UTF-8");
        } finally {
            try {
                fos.close();
            } catch (IOException ignore) {
            }
        }
    }

    private Schedule read(Context ctx) throws Exception {
        FileInputStream in = null;
        try {
            in = ctx.openFileInput(FILENAME);
            return new Persister().read(Schedule.class, in);
        } catch (FileNotFoundException notFound) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignore) {
            }
        }
    }
}
