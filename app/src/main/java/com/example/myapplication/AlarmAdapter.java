package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    private List<Alarm> alarms;

    public AlarmAdapter(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.textViewAlarmTime.setText(alarm.getTimeString());
        holder.switchAlarm.setChecked(alarm.isOn());
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public void removeAlarm(int position) {
        alarms.remove(position);
        notifyItemRemoved(position);
    }

    static class AlarmViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAlarmTime;
        Switch switchAlarm;

        AlarmViewHolder(View itemView) {
            super(itemView);
            textViewAlarmTime = itemView.findViewById(R.id.textViewAlarmTime);
            switchAlarm = itemView.findViewById(R.id.switchAlarm);
        }
    }
}