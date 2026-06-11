package com.example.fitnessapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitnessapp.R;
import com.example.fitnessapp.data.AppDatabase;
import com.example.fitnessapp.data.Workout;
import com.example.fitnessapp.data.WorkoutDatabase;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EditWorkoutActivity extends AppCompatActivity implements OnMapReadyCallback {

    private EditText etName, etDuration;
    private Button btnUpdate, btnDelete;

    private WorkoutDatabase db;

    private int workoutId;

    private double lat;
    private double lng;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        db = AppDatabase.getInstance(this);

        etName = findViewById(R.id.etName);
        etDuration = findViewById(R.id.etDuration);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Intent data
        workoutId = getIntent().getIntExtra("id", -1);
        String name = getIntent().getStringExtra("name");
        int duration = getIntent().getIntExtra("duration", 0);

        lat = getIntent().getDoubleExtra("lat", 0);
        lng = getIntent().getDoubleExtra("lng", 0);

        // Set fields
        if (name != null) etName.setText(name);
        etDuration.setText(String.valueOf(duration));

        // UPDATE
        btnUpdate.setOnClickListener(v -> {

            String newName = etName.getText().toString().trim();
            String newDuration = etDuration.getText().toString().trim();

            if (newName.isEmpty() || newDuration.isEmpty()) return;

            // We keep existing date from DB (important conceptually)
            Workout workout = new Workout(
                    newName,
                    Integer.parseInt(newDuration),
                    "", // date intentionally not edited here
                    lat,
                    lng
            );

            workout.setId(workoutId);

            db.workoutDao().update(workout);

            finish();
        });

        // DELETE
        btnDelete.setOnClickListener(v -> {

            Workout workout = new Workout("", 0, "", 0, 0);
            workout.setId(workoutId);

            db.workoutDao().delete(workout);

            finish();
        });

        // MAP
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // TOOLBAR (SAFE)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng location = new LatLng(lat, lng);

        mMap.addMarker(new MarkerOptions()
                .position(location)
                .title("Workout Location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f));
    }
}