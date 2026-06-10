package com.example.fitnessapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Workout.class},
        version = 1,
        exportSchema = false
)
public abstract class WorkoutDatabase extends RoomDatabase {

    public abstract WorkoutDao workoutDao();

}