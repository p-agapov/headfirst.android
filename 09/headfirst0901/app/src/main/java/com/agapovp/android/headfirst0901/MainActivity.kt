package com.agapovp.android.headfirst0901

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.agapovp.android.headfirst0901.DetailActivity.Companion.EXTRA_WORKOUT_ID

class MainActivity : AppCompatActivity(), WorkoutListFragment.Companion.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun itemClicked(id: Long) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra(EXTRA_WORKOUT_ID, id)
        )
    }
}
