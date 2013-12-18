package com.mtrstudios.Fahrplan30c3;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mtrstudios.Fahrplan30c3.Data.Event;

import java.util.List;

/**
 * Created by markus on 01.06.13.
 */
public class EventListAdapter extends ArrayAdapter<Event> {

    private final Activity ctx;

    static class ViewHolder {
        public TextView txTitle;
        public TextView txSpeaker;
        public TextView txAbstract;
        public TextView txTime;
        public View txTrackIndicator;

    }

    public EventListAdapter(Activity context, int textViewResourceId, List<Event> objects) {
        super(context, textViewResourceId, objects);
        this.ctx = context;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            final LayoutInflater inflater = ctx.getLayoutInflater();
            rowView = inflater.inflate(R.layout.event_list_row, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txTitle = (TextView) rowView.findViewById(R.id.tx_title);
            viewHolder.txSpeaker = (TextView) rowView.findViewById(R.id.tx_speaker);
            viewHolder.txAbstract = (TextView) rowView.findViewById(R.id.tx_abstract);
            viewHolder.txTime = (TextView) rowView.findViewById(R.id.tx_time);
            viewHolder.txTrackIndicator = rowView.findViewById(R.id.spc_track);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        final Event event = getItem(position);
        holder.txTitle.setText(event.getTitle());
        holder.txSpeaker.setText(event.getSpeakerNames());
        holder.txTime.setText(event.getStart());
        holder.txAbstract.setText(event.getAbstract_description());
        holder.txTrackIndicator.setBackgroundColor(event.getTrackColor());

        return rowView;
    }
}
