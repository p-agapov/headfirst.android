package com.agapovp.android.headfirst1301

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_pizza_detail.*

class PizzaDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_detail)

        setSupportActionBar(activity_pizza_detail_toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pizzaId = intent.extras?.getInt(EXTRA_PIZZA_ID) ?: 0

        with(Pizza.pizzas[pizzaId].name) {
            activity_pizza_detail_text.text = this
            activity_pizza_detail_image.contentDescription = this
        }
        activity_pizza_detail_image.setImageDrawable(
            ContextCompat.getDrawable(this, Pizza.pizzas[pizzaId].imageResourceId)
        )
    }

    companion object {
        const val EXTRA_PIZZA_ID = "pizzaId"
    }
}
