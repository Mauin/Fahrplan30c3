package com.mtrstudios.Fahrplan30c3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mtrstudios.Fahrplan30c3.Data.Event;

/**
 * A fragment representing a single Talk detail screen.
 * This fragment is either contained in a {@link TalkListActivity}
 * in two-pane mode (on tablets) or a {@link TalkDetailActivity}
 * on handsets.
 */
public class TalkDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Event event;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TalkDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //TODO: load item
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talk_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (event!= null) {
            ((TextView) rootView.findViewById(R.id.talk_detail)).setText(event.getTitle());
        }

        return rootView;
    }
}
