package com.agapovp.android.headfirst0901

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class WorkoutListFragment : ListFragment() {

    private var listener: Listener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.listener = context as Listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listAdapter = ArrayAdapter(
            inflater.context,
            android.R.layout.simple_list_item_1,
            Workout.workouts.map { it.name })

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        listener?.itemClicked(id)
    }

    companion object {
        interface Listener {
            fun itemClicked(id: Long)
        }
    }
}
