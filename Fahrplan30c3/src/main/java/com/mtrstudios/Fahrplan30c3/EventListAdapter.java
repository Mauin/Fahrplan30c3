package com.mtrstudios.Fahrplan30c3;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mtrstudios.Fahrplan30c3.Data.Event;
import com.mtrstudios.Fahrplan30c3.R;

import java.util.List;

/**
 * Created by markus on 01.06.13.
 */
public class EventListAdapter extends ArrayAdapter<Event> {

    public static final int GREEN = Color.parseColor("#99CC00");
    public static final int YELLOW = Color.parseColor("#FFBB33");
    public static final int RED = Color.parseColor("#FF4444");
    private final Activity ctx;

    static class ViewHolder {
        public TextView txTitle;
        public TextView txSpeaker;
        public TextView txRoom;
        public TextView txAbstract;

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
            viewHolder.txRoom = (TextView) rowView.findViewById(R.id.tx_room);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        final Event item = getItem(position);
        holder.txTitle.setText(item.getTitle());
        holder.txSpeaker.setText(item.getSpeakerNames());
        holder.txRoom.setText(item.getRoom());
        holder.txAbstract.setText(item.getAbstract_description());

        return rowView;
    }
}
