package com.agapovp.android.headfirst0701

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_top_level.*

class TopLevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)

        list_options.setOnItemClickListener { _, _, position, _ ->
            if (position == 0) {
                startActivity(
                    Intent(this, DrinkCategoryActivity::class.java)
                )
            }
        }
    }
}
