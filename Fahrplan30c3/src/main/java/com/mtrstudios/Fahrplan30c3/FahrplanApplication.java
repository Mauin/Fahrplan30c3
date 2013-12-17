package com.mtrstudios.Fahrplan30c3;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mtrstudios.Fahrplan30c3.Data.Fahrplan;
import com.mtrstudios.Fahrplan30c3.Data.Schedule;
import com.mtrstudios.Fahrplan30c3.service.FileScheduleService;
import com.mtrstudios.Fahrplan30c3.service.ScheduleService;
import org.simpleframework.xml.core.Persister;

/**
 * Created by markus on 15.12.13.
 */
public class FahrplanApplication extends Application {
    private ScheduleService service = new FileScheduleService();

    private Fahrplan fahrplan;

    public ScheduleService getService() {
        return service;
    }

    public void setService(ScheduleService service) {
        this.service = service;
    }

    protected void getFahrplan(final Context ctx, final Response.Listener<Fahrplan> listener) {
        getFahrplan(ctx, listener, false);
    }

    /**
     * Retrieves the current {@link com.mtrstudios.Fahrplan30c3.Data.Fahrplan}.
     *
     * @param listener     the listener that will be called to handle loading
     * @param forceRefresh If {@code forceRefresh} is set to true the current version online is checked against the
     *                     cached version. If set to false, the chached version is used if present.
     */
    protected void getFahrplan(final Context ctx, final Response.Listener<Fahrplan> listener, boolean forceRefresh) {
        if (fahrplan == null || forceRefresh) {
            Schedule currentSchedule = getCurrentSchedule(ctx);
            if (currentSchedule == null || forceRefresh) {
                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest request = new StringRequest("http://events.ccc.de/congress/2013/Fahrplan/schedule.xml",
                        createSuccessListener(ctx, listener), createErrorListener(ctx));
                queue.add(request);
            } else {
                fahrplan = new Fahrplan(currentSchedule);
                listener.onResponse(fahrplan);
            }
        } else {
            listener.onResponse(fahrplan);
        }
    }

    private Schedule getCurrentSchedule(Context ctx) {
        Schedule currentSchedule = null;
        try {
            currentSchedule = getService().getSchedule(ctx);
        } catch (Exception e) {
            Log.e(FahrplanApplication.class.getName(), "Unable to load current schedule. Overriding.", e);
        }
        return currentSchedule;
    }

    private Response.ErrorListener createErrorListener(final Context ctx) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(FahrplanApplication.class.getName(), volleyError.getLocalizedMessage());
                Toast.makeText(ctx, "Unable to update schedule.\n" + volleyError.getLocalizedMessage(),
                        Toast.LENGTH_LONG).show();
            }
        };
    }

    private Response.Listener<String> createSuccessListener(final Context ctx, final Response.Listener<Fahrplan> listener) {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Schedule currentSchedule = null;
                try {
                    currentSchedule = getCurrentSchedule(ctx);
                    final Schedule updatedSchedule = new Persister().read(Schedule.class, s);
                    if (currentSchedule == null || currentSchedule.getVersion() < updatedSchedule.getVersion()) {
                        onScheduleChanged(updatedSchedule, ctx);
                    } else {
                        Toast.makeText(ctx, "Nothing has changed!", Toast.LENGTH_SHORT).show();
                        if (fahrplan == null) {
                            fahrplan = new Fahrplan(currentSchedule);
                        }
                    }
                } catch (Exception e) {
                    Log.e(FahrplanApplication.class.getName(), "Invalid schedule", e);
                    Log.d(FahrplanApplication.class.getName(), s);
                    if (fahrplan == null && currentSchedule != null) {
                        fahrplan = new Fahrplan(currentSchedule);
                    }
                    Toast.makeText(ctx, "Unable to update schedule.\n" + e.getLocalizedMessage(),
                            Toast.LENGTH_LONG).show();
                }
                listener.onResponse(fahrplan);
            }
        };
    }

    private void onScheduleChanged(Schedule updatedSchedule, Context ctx) {
        try {
            getService().setSchedule(ctx, updatedSchedule);
        } catch (Exception e) {
            Log.e(FahrplanApplication.class.getName(), "Unable to store schedule", e);
        }
        fahrplan = new Fahrplan(updatedSchedule);
        Toast.makeText(ctx, "Schedule updated!", Toast.LENGTH_LONG).show();
    }
}
