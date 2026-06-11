package com.example.fitnessapp.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fitnessapp.R;
import com.example.fitnessapp.data.AppDatabase;
import com.example.fitnessapp.data.Workout;
import com.example.fitnessapp.data.WorkoutDatabase;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.adapter.WorkoutAdapter;


public class MainActivity extends AppCompatActivity {

    private WorkoutDatabase db;
    private List<Workout> workouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);

        workouts = db.workoutDao().getAll();

        RecyclerView recyclerView =
                findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        WorkoutAdapter adapter =
                new WorkoutAdapter(workouts);

        recyclerView.setAdapter(adapter);
    }
}