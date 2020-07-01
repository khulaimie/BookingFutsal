package com.example.khulaimi.bookingfutsal;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Schedules.class}, version = 1, exportSchema = false)
public abstract class SchedulesRoomDatabase extends RoomDatabase {

    public abstract SchedulesDao SchedulesDao();
    private static SchedulesRoomDatabase INSTANCE;

    static SchedulesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SchedulesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SchedulesRoomDatabase.class, "schedules")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SchedulesDao mDao;
        String[] Schedules = {};

        PopulateDbAsync(SchedulesRoomDatabase db) {
            mDao = db.SchedulesDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= Schedules.length - 1; i++) {
                Schedules Schedule = new Schedules(Schedules[i], "");
                mDao.insert(Schedule);
            }
            return null;
        }
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
        new RoomDatabase.Callback(){

            @Override
            public void onOpen (@NonNull SupportSQLiteDatabase db){
                super.onOpen(db);
                new PopulateDbAsync(INSTANCE).execute();
            }
        };
}
