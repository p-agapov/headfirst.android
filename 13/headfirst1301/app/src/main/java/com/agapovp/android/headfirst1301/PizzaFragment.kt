package com.agapovp.android.headfirst1301

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PizzaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val pizzaNames = arrayOfNulls<String>(Pizza.pizzas.size)
        val pizzaImages = IntArray(Pizza.pizzas.size)

        for (i in Pizza.pizzas.indices) {
            pizzaNames[i] = Pizza.pizzas[i].name
            pizzaImages[i] = Pizza.pizzas[i].imageResourceId
        }

        return (inflater.inflate(R.layout.fragment_pizza, container, false)
                as RecyclerView)
            .apply {
                adapter = CaptionedImagesAdapter(
                    pizzaNames,
                    pizzaImages
                ) { position ->
                    startActivity(
                        Intent(activity, PizzaDetailActivity::class.java).apply {
                            putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position)
                        }
                    )
                }
                layoutManager = GridLayoutManager(
                    activity,
                    resources.getInteger(R.integer.fragment_pizza_spanCount)
                )
            }
    }
}
