package com.example.fitnessapp.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.fitnessapp.R;
import com.example.fitnessapp.data.AppDatabase;
import com.example.fitnessapp.data.Workout;
import com.example.fitnessapp.data.WorkoutDatabase;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddWorkoutActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;

    private double latitude = 0;
    private double longitude = 0;

    private static final int LOCATION_REQUEST_CODE = 100;

    private EditText etName;
    private EditText etDuration;

    private WorkoutDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        db = AppDatabase.getInstance(this);

        etName = findViewById(R.id.etName);
        etDuration = findViewById(R.id.etDuration);

        Button btnSave = findViewById(R.id.btnSave);
        Button btnLocation = findViewById(R.id.btnLocation);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        // LOCATION BUTTON
        btnLocation.setOnClickListener(v -> getLocation());

        // SAVE BUTTON
        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String durationText = etDuration.getText().toString().trim();

            if (name.isEmpty() || durationText.isEmpty()) return;

            int duration = Integer.parseInt(durationText);

            // ✅ AUTO DATE (your fix applied here)
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    .format(new Date());

            Workout workout = new Workout(name, duration, date, latitude, longitude);

            db.workoutDao().insert(workout);

            finish();
        });
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE
            );
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST_CODE &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}