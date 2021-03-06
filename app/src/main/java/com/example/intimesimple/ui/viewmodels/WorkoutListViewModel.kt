package com.example.intimesimple.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.intimesimple.data.local.Workout
import com.example.intimesimple.repositories.WorkoutRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class WorkoutListViewModel @ViewModelInject constructor(
        private val workoutRepository: WorkoutRepository
): ViewModel() {


    val workouts = workoutRepository.getAllWorkouts().asLiveData()

    fun addWorkout(workout: Workout){
        viewModelScope.launch {
            workoutRepository.insertWorkout(workout)
        }
    }

    fun deleteWorkout(workout: Workout){
        Timber.d("Deleting workout: ${workout.id}")
        viewModelScope.launch {
            workoutRepository.deleteWorkout(workout)
        }
    }

    fun deleteWorkoutWithId(wId: Long){
        viewModelScope.launch {
            workoutRepository.deleteWorkoutWithId(wId)
        }
    }
}