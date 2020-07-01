package com.example.khulaimi.bookingfutsal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface SchedulesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Schedules schedules);

    @Query("DELETE FROM schedules")
    void deleteAll();

    @Query("SELECT * FROM schedules ORDER BY schedule ASC")
    LiveData<List<Schedules>> getAll();

    @Query("UPDATE schedules SET done = 1 WHERE schedule = :schedule")
    void setDone(String schedule);

    @Query("UPDATE schedules SET done = 0 WHERE schedule = :schedule")
    void setUnDone(String schedule);
}
