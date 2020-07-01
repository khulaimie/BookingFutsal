package com.example.khulaimi.bookingfutsal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SchedulesViewModel extends AndroidViewModel {

    private SchedulesRepository mRepository;

    private LiveData<List<Schedules>> mAllScheduless;

    public SchedulesViewModel(Application application) {
        super(application);
        mRepository = new SchedulesRepository(application);
        mAllScheduless = mRepository.getAll();
    }

    LiveData<List<Schedules>> getAll() { return mAllScheduless; }

    public void insert(Schedules Schedules) { mRepository.insert(Schedules); }

    public void deletesAll() { mRepository.deletesAll(); }

    public void setDone(Schedules schedules, Boolean done) {
        mRepository.setDone(schedules, done);
    }
}