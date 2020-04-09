package com.agapovp.android.headfirst1101

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.agapovp.android.headfirst1101.DetailActivity.Companion.EXTRA_WORKOUT_ID
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WorkoutListFragment.Companion.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun itemClicked(id: Long) {
        if (fragment_container != null) {
            val fragment = WorkoutDetailFragment().apply { setWorkout(id) }
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, fragment)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                addToBackStack(null)
                commit()
            }
        } else {
            startActivity(
                Intent(this, DetailActivity::class.java)
                    .putExtra(EXTRA_WORKOUT_ID, id)
            )
        }
    }
}
