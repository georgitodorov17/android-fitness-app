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

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.adapter.WorkoutAdapter;

import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WorkoutDatabase db;
    private List<Workout> workouts = new ArrayList<>();
    private WorkoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        workouts = new ArrayList<>();

        adapter = new WorkoutAdapter(workouts, workout -> {
            Intent intent = new Intent(MainActivity.this, EditWorkoutActivity.class);

            intent.putExtra("id", workout.getId());
            intent.putExtra("name", workout.getName());
            intent.putExtra("duration", workout.getDuration());
            intent.putExtra("date", workout.getDate());
            intent.putExtra("lat", workout.getLatitude());
            intent.putExtra("lng", workout.getLongitude());

            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        loadWorkouts();
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWorkoutActivity.class);
            startActivity(intent);
        });
    }

    private void loadWorkouts() {
        workouts.clear();
        workouts.addAll(db.workoutDao().getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (db == null || recyclerView == null || adapter == null) {
            return;
        }

        workouts.clear();
        workouts.addAll(db.workoutDao().getAll());

        adapter.notifyDataSetChanged();
        loadWorkouts();
    }
}