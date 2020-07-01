package com.example.khulaimi.bookingfutsal;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SchedulesListAdapter extends RecyclerView.Adapter<SchedulesListAdapter.SchedulesViewHolder> {

    private final LayoutInflater mInflater;
    private List<Schedules> mSchedule; // Cached copy of Scheduless
    private final MainActivity app;

    SchedulesListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        app = (MainActivity) context;
    }

    @Override
    public SchedulesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SchedulesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SchedulesViewHolder holder, int position) {
        if (mSchedule != null) {
            final Schedules current = mSchedule.get(position);
            Boolean done = current.getDone();
            holder.SchedulesItemView.setText(current.getSchedules() + " - " + current.getTime());
            CheckBox ch = holder.SchedulesCheckBox;
            ch.setChecked(done);
            if (done) {
                holder.SchedulesItemView.setTypeface(null, Typeface.BOLD_ITALIC);
            } else {
                holder.SchedulesItemView.setTypeface(null, Typeface.NORMAL);
            }

            ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                    app.getModel().setDone(current, isChecked);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.SchedulesItemView.setText("No Schedules");
        }
    }

    void setScheduless(List<Schedules> Schedule){
        mSchedule = Schedule;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mSchedule has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSchedule != null)
            return mSchedule.size();
        else return 0;
    }

    class SchedulesViewHolder extends RecyclerView.ViewHolder {
        private final TextView SchedulesItemView;
        private final CheckBox SchedulesCheckBox;

        private SchedulesViewHolder(View itemView) {
            super(itemView);
            SchedulesItemView = itemView.findViewById(R.id.textView);
            SchedulesCheckBox = itemView.findViewById(R.id.checkbox);
        }
    }
}