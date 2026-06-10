package com.example.fitnessapp.repository;

import com.example.fitnessapp.data.Workout;
import com.example.fitnessapp.data.WorkoutDao;

import java.util.List;

public class WorkoutRepository {

    private final WorkoutDao workoutDao;

    public WorkoutRepository(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    public void insert(Workout workout) {
        workoutDao.insert(workout);
    }

    public void update(Workout workout) {
        workoutDao.update(workout);
    }

    public void delete(Workout workout) {
        workoutDao.delete(workout);
    }

    public List<Workout> getAll() {
        return workoutDao.getAll();
    }
}