## 🏋️ Fitness App

**Fitness App** is a simple Android application that helps users track their workouts.  
Users can add, view, edit, and delete workout records, as well as store the location where the workout took place using Google Maps.

The app is designed as a beginner-friendly fitness tracker demonstrating Android development concepts such as **Room database, RecyclerView, Activities, and Google Maps integration**.

---

## 🚀 How it works

The app follows a simple user flow:

1. The user opens the app and sees a list of saved workouts.
2. The user taps the **"+" button** to add a new workout.
3. The user enters workout details (name, duration) and optionally captures their location.
4. The workout is saved into a **local Room database**.
5. The workout appears in the main list (RecyclerView).
6. The user can tap on a workout to:
   - Edit workout details
   - Delete workout
   - View location on a Google Map
7. All changes are immediately saved locally.

---

## 🏗️ Architecture

The app uses a simple MV-like structure:

- **Activities**
  - `MainActivity` → displays workout list
  - `AddWorkoutActivity` → creates new workouts
  - `EditWorkoutActivity` → edits/deletes workouts + map view

- **Database Layer**
  - Room Database
  - `Workout` entity
  - `WorkoutDao`
  - `AppDatabase`

- **UI Components**
  - RecyclerView (workout list)
  - Material Toolbar
  - Floating Action Button

- **External Services**
  - Google Maps SDK (for workout location display)
  - FusedLocationProvider (GPS location)

---

## 🔁 User Flow Diagram

- Launch app → Workout list screen
- Tap **+** → Add workout screen
- Save workout → returns to list
- Tap workout → Edit/Delete screen
- View workout location on map

---

## 🛠️ Technologies & Versions

| Technology | Version |
|------------|--------|
| Android Studio | 2026.1.1 (Quail) |
| Java | 17 |
| Room Database | 2.6+ |
| Material Design | 1.12+ |
| Google Maps SDK | Latest (Play Services Maps) |
| RecyclerView | AndroidX |
| Min SDK | 24 |

---

## ⚙️ Setup Instructions

### 1. Clone the repository
```bash
git clone <your-repo-url>
