package com.agapovp.android.headfirst1001

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_workout_detail.*

class WorkoutDetailFragment : Fragment() {

    private var workoutId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong(WORKOUT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onStart() {
        super.onStart()

        tv_title.text = Workout.workouts[workoutId.toInt()].name
        tv_description.text = Workout.workouts[workoutId.toInt()].description
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putLong(WORKOUT_ID, workoutId)
    }

    fun setWorkout(id: Long) {
        this.workoutId = id
    }

    companion object {
        const val WORKOUT_ID = "workoutId"
    }
}
