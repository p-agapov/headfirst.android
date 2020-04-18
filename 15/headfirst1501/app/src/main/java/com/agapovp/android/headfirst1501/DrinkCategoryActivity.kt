package com.agapovp.android.headfirst1501

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drink_category.*

class DrinkCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        list_drinks.apply {
            adapter = ArrayAdapter(
                this@DrinkCategoryActivity,
                android.R.layout.simple_list_item_1,
                Drink.drinks
            )

            setOnItemClickListener { _, _, _, id ->
                startActivity(
                    Intent(this@DrinkCategoryActivity, DrinkActivity::class.java)
                        .putExtra(EXTRA_DRINK_ID, id.toInt())
                )
            }
        }
    }
}
