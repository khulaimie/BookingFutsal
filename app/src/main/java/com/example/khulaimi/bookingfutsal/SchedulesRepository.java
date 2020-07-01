package com.example.khulaimi.bookingfutsal;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SchedulesRepository {
    private SchedulesDao mSchedulesDao;
    private LiveData<List<Schedules>> mAllScheduless;

    SchedulesRepository(Application application) {
        SchedulesRoomDatabase db = SchedulesRoomDatabase.getDatabase(application);
        mSchedulesDao = db.SchedulesDao();
        mAllScheduless = mSchedulesDao.getAll();
    }

    LiveData<List<Schedules>> getAll() {
        return mAllScheduless;
    }

    public void insert (Schedules Schedules) {
        new insertAsyncTask(mSchedulesDao).execute(Schedules);
    }
    public void deletesAll () {
        new deletesAllAsyncTask(mSchedulesDao).execute();
    }
    public void setDone (Schedules schedules, Boolean done) {
        new setDoneAsyncTask(mSchedulesDao, schedules).execute(done);
    }

    private static class insertAsyncTask extends AsyncTask<Schedules, Void, Void> {

        private SchedulesDao mAsyncTaskDao;

        insertAsyncTask(SchedulesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Schedules... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deletesAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private SchedulesDao mAsyncTaskDao;

        deletesAllAsyncTask(SchedulesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class setDoneAsyncTask extends AsyncTask<Boolean, Void, Void> {

        private SchedulesDao mAsyncTaskDao;
        private Schedules mschedules;

        setDoneAsyncTask(SchedulesDao dao, Schedules schedules) {
            mAsyncTaskDao = dao;
            mschedules = schedules;
        }

        @Override
        protected Void doInBackground(final Boolean... params) {
            Boolean done = params[0];
            if (done) {
                mAsyncTaskDao.setDone(mschedules.getSchedules());
            } else {
                mAsyncTaskDao.setUnDone(mschedules.getSchedules());
            }
            return null;
        }
    }
}
