package com.example.intimesimple.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Workout::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}