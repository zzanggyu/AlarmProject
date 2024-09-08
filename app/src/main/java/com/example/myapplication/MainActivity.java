package com.example.myapplication;

import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlarms;
    private AlarmAdapter alarmAdapter;
    private List<Alarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewAlarms = findViewById(R.id.recyclerViewAlarms);
        FloatingActionButton fabAddAlarm = findViewById(R.id.fabAddAlarm);

        alarms = new ArrayList<>();
        alarmAdapter = new AlarmAdapter(alarms);
        recyclerViewAlarms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlarms.setAdapter(alarmAdapter);

        fabAddAlarm.setOnClickListener(v -> showTimePickerDialog());

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(alarmAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerViewAlarms);
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
            this,
            (view, hourOfDay, minute) -> {
        Alarm newAlarm = new Alarm(hourOfDay, minute);
        alarms.add(newAlarm);
        alarmAdapter.notifyItemInserted(alarms.size() - 1);
        // TODO: 여기에 알람 설정 로직 추가
    },
        0, 0, false);
        timePickerDialog.show();
    }

    private class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
        private AlarmAdapter adapter;

        SwipeToDeleteCallback(AlarmAdapter adapter) {
            super(0, ItemTouchHelper.LEFT);
            this.adapter = adapter;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            adapter.removeAlarm(position);
            // TODO: 여기에 알람 취소 로직 추가
        }
    }}