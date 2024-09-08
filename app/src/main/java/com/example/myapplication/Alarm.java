package com.example.myapplication;

public class Alarm {
    private int hour;
    private int minute;
    private boolean isOn;

    public Alarm(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        this.isOn = true;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String getTimeString() {
        return String.format("%02d:%02d", hour, minute);
    }
}



