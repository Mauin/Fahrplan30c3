package com.mtrstudios.Fahrplan30c3;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.common.base.Optional;
import com.mtrstudios.Fahrplan30c3.Data.Event;
import com.mtrstudios.Fahrplan30c3.Data.Fahrplan;

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
            final int id = (int) getArguments().getLong(ARG_ITEM_ID);
            ((FahrplanApplication) getActivity().getApplication()).getFahrplan(getActivity(), new Response.Listener<Fahrplan>() {
                @Override
                public void onResponse(Fahrplan fahrplan) {
                    final Optional<Event> eventOptional = fahrplan.getEvent(id);
                    if (eventOptional.isPresent()){
                        event = eventOptional.get();
                    } else {
                        getActivity().finish();
                    }
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talk_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (event != null) {
            ((TextView) rootView.findViewById(R.id.talk_time)).setText(event.getStart());
            ((TextView) rootView.findViewById(R.id.talk_hall)).setText(event.getRoom());
            ((TextView) rootView.findViewById(R.id.talk_title)).setText(event.getTitle());
            ((TextView) rootView.findViewById(R.id.talk_subtitle)).setText(event.getSubtitle());
            ((TextView) rootView.findViewById(R.id.talk_abstract)).setText(event.getAbstract_description());

            ((TextView) rootView.findViewById(R.id.talk_speaker_name)).setText(event.getSpeakerNames());

            /*
            NetworkImageView speakerImageView = (NetworkImageView) rootView.findViewById(R.id.talk_speaker_image);
            ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(getActivity()), new ImageLoader.ImageCache() {
                @Override
                public void putBitmap(String key, Bitmap value) { }

                @Override
                public Bitmap getBitmap(String key) {
                    return null;
                }
            });

            speakerImageView.setImageUrl("ENTER URL HERE", imageLoader);
            */
        }

        return rootView;
    }
}
