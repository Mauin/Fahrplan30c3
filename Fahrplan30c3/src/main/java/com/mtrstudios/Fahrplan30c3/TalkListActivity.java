package com.mtrstudios.Fahrplan30c3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.volley.Response;
import com.mtrstudios.Fahrplan30c3.Data.Fahrplan;

import java.util.Locale;


/**
 * An activity representing a list of Talks. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link TalkDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link TalkListFragment} and the item details
 * (if present) is a {@link TalkDetailFragment}.
 * <p/>
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_talk_list);

        final TalkListFragment talkListFragment = (TalkListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.talk_list);
        if (findViewById(R.id.talk_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            talkListFragment.setActivateOnItemClick(true);
        }

        ((FahrplanApplication) getApplication()).getFahrplan(this, new Response.Listener<Fahrplan>() {
            @Override
            public void onResponse(Fahrplan fahrplan) {
                if (fahrplan != null) {
                    Log.i(TalkListActivity.class.getName(), "Fahrplan loaded :D" + fahrplan);
                    Toast.makeText(TalkListActivity.this,
                            String.format(Locale.US, "Using Fahrplan v%.2f", fahrplan.getVersion()),
                            Toast.LENGTH_LONG).show();
                    talkListFragment.setEvents(fahrplan.getAllEvents());
                } else {
                    Toast.makeText(TalkListActivity.this, "No Fahrplan :(", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.talk_list, menu);
        return true;
    }

    /**
     * Callback method from {@link TalkListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(long id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putLong(TalkDetailFragment.ARG_ITEM_ID, id);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                doRefresh();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doRefresh() {
        ((FahrplanApplication) getApplication()).getFahrplan(this, new Response.Listener<Fahrplan>() {
            @Override
            public void onResponse(Fahrplan fahrplan) {
                if (fahrplan != null) {
                    Log.i(TalkListActivity.class.getName(), "Fahrplan loaded :D" + fahrplan);
                    Toast.makeText(TalkListActivity.this,
                            String.format(Locale.US, "Using Fahrplan v%.2f", fahrplan.getVersion()),
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(TalkListActivity.this, "No Fahrplan :(", Toast.LENGTH_LONG).show();
                }
            }
        }, true);
    }
}
