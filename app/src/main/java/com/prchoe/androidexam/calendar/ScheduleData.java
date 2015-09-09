package com.prchoe.androidexam.calendar;

/**
 * Created by massivCode on 2015-09-09.
 */
public class ScheduleData {

    private String content;
    private int hour;
    private int minute;

    public ScheduleData(String content, int hour, int minute) {
        this.content = content;
        this.hour = hour;
        this.minute = minute;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
