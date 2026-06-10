package com.example.fitnessapp.data;

import android.content.Context;

import androidx.room.Room;

public class AppDatabase {

    private static WorkoutDatabase instance;

    public static WorkoutDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WorkoutDatabase.class,
                            "fittracker_db"
                    )
                    .allowMainThreadQueries() // IMPORTANT for school projects
                    .build();
        }
        return instance;
    }
}