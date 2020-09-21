package com.example.assignment3;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {StepsSQL.class}, version = 2, exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class StepsDatabase extends RoomDatabase {
    public abstract StepsDAO stepsDAO();

    private static volatile StepsDatabase INSTANCE;

    static StepsDatabase getDatabase (final Context context)
    {
        if (INSTANCE == null) {
            synchronized (StepsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    StepsDatabase.class, "steps_database")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
    }
