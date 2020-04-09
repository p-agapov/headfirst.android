package com.agapovp.android.headfirst1001

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val workDetailFragment =
            supportFragmentManager.findFragmentById(R.id.f_workout_detail) as WorkoutDetailFragment

        workDetailFragment.setWorkout(intent.extras?.getLong(EXTRA_WORKOUT_ID) ?: return)
    }

    companion object {
        const val EXTRA_WORKOUT_ID = "workout.id"
    }
}
