package com.mtrstudios.Fahrplan30c3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.mtrstudios.Fahrplan30c3.Data.Conference;
import com.mtrstudios.Fahrplan30c3.Data.Day;
import com.mtrstudios.Fahrplan30c3.Data.Room;
import com.mtrstudios.Fahrplan30c3.Data.Schedule;
import com.mtrstudios.Fahrplan30c3.Misc.MyVolley;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringReader;


/**
 * An activity representing a list of Talks. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link TalkDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link TalkListFragment} and the item details
 * (if present) is a {@link TalkDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link TalkListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class TalkListActivity extends FragmentActivity
        implements TalkListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private String xmlResponse;

    private void parseSchedule(String xmlSchedule) {
        Schedule schedule = null;
        Serializer serializer = new Persister();
        try {
            Log.i("XMLParser", "Parsing XML Schedule");
            schedule = serializer.read(Schedule.class, xmlResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (schedule != null) {
            Log.i("XMLParser", "Parsing successful");
            Log.i("Schedule", "Version: " + schedule.getVersion());
            Log.i("Schedule", "Welcome to event: " + schedule.getConference().getTitle());
            Log.i("Schedule", "Number of days: " + schedule.getDays().size());

            int eventCount = 0;
            for (Day day : schedule.getDays()) {
                for (Room room : day.getRooms()) {
                    eventCount += room.getEvents().size();
                }
            }

            Log.i("Schedule", "Number of events: " + eventCount);
        }

    }

    private Response.Listener<String> createRequestSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                xmlResponse = response;
                Log.i("RequestQueue", "Schedule get!");
                Toast.makeText(getBaseContext(), "XML Get!", Toast.LENGTH_SHORT).show();

                parseSchedule(response);
            }
        };
    }
    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                xmlResponse = null;
                Toast.makeText(getBaseContext(), "Failed to get data...", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Just get the XML-Fahrplan
        RequestQueue queue = MyVolley.getRequestQueue();
        StringRequest request = new StringRequest(Request.Method.GET, "http://events.ccc.de/congress/2013/Fahrplan/schedule.xml", createRequestSuccessListener(), createRequestErrorListener());
        queue.add(request);
        Log.i("RequestQueue", "Added request for XML-Schedule");

        setContentView(R.layout.activity_talk_list);

        if (findViewById(R.id.talk_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((TalkListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.talk_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link TalkListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(TalkDetailFragment.ARG_ITEM_ID, id);
            TalkDetailFragment fragment = new TalkDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.talk_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, TalkDetailActivity.class);
            detailIntent.putExtra(TalkDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
