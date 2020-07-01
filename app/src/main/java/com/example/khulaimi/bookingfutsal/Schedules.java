package com.example.khulaimi.bookingfutsal;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedules")
public class Schedules {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "schedule")
    public String mSchedule;

    @NonNull
    @ColumnInfo(name = "time")
    public String mTime;

    public void setmDone(@NonNull Boolean mDone) {
        this.mDone = mDone ? 1 : 0;
    }

    @NonNull
    @ColumnInfo(name = "done")
    public Integer mDone;

    public Schedules(@NonNull String schedule, @NonNull String time) {
        this.mSchedule = schedule;
        this.mTime = time;
        this.mDone = 0;
    }

    public void setDone(){this.setmDone(true);}
    public void setUndone(){this.setmDone(false);}
    public String getSchedules(){return this.mSchedule;}
    public String getTime(){return this.mTime;}
    public Boolean getDone(){if (this.mDone == 1) {return true;} else {return false;}}
}
