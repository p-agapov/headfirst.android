package com.agapovp.android.headfirst0901

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_workout_detail.*

class WorkoutDetailFragment : Fragment() {

    private var workoutId: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onStart() {
        super.onStart()

        workoutId?.let {
            val id: Int = it.toInt()
            tv_title.text = Workout.workouts[id].name
            tv_description.text = Workout.workouts[id].description
        }
    }

    fun setWorkout(id: Long?) {
        this.workoutId = id
    }
}
