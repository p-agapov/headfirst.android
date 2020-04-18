package com.agapovp.android.headfirst1501

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drink.*

class DrinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val drink = Drink.drinks[intent.extras?.get(EXTRA_DRINK_ID) as Int]

        name.text = drink.name
        description.text = drink.description
        photo.apply {
            setImageResource(drink.imageResourceId)
            contentDescription = drink.name
        }
    }
}
