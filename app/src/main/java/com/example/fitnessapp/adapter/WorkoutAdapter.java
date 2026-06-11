package com.example.fitnessapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.data.Workout;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    // Data list
    private List<Workout> workouts;

    // Click listener interface
    public interface OnWorkoutClickListener {
        void onClick(Workout workout);
    }

    private OnWorkoutClickListener listener;

    // Constructor
    public WorkoutAdapter(List<Workout> workouts, OnWorkoutClickListener listener) {
        this.workouts = workouts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout, parent, false);

        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {

        Workout workout = workouts.get(position);

        holder.tvName.setText(workout.getName());

        holder.tvDuration.setText(
                "Duration: " + workout.getDuration() + " min"
        );

        holder.tvDate.setText(
                "Date: " + workout.getDate()
        );

        // CLICK EVENT (important for edit screen)
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(workout);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    // ViewHolder
    static class WorkoutViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDuration;
        TextView tvDate;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}