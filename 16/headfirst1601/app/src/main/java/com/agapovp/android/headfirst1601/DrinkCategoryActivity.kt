package com.agapovp.android.headfirst1601

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agapovp.android.headfirst1601.DrinkActivity.Companion.EXTRA_DRINK_ID
import kotlinx.android.synthetic.main.activity_drink_category.*

class DrinkCategoryActivity : AppCompatActivity() {

    private lateinit var starbuzzDatabaseHelper: StarbuzzDatabaseHelper
    private lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        starbuzzDatabaseHelper = StarbuzzDatabaseHelper(this)

        try {
            cursor = starbuzzDatabaseHelper
                .readableDatabase
                .query(
                    "DRINK",
                    arrayOf("_id", "NAME"),
                    null, null, null, null, null
                )

            list_drinks.apply {
                adapter = SimpleCursorAdapter(
                    this@DrinkCategoryActivity,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    arrayOf("NAME"),
                    intArrayOf(android.R.id.text1),
                    0
                )

                setOnItemClickListener { _, _, _, id ->
                    startActivity(
                        Intent(this@DrinkCategoryActivity, DrinkActivity::class.java)
                            .putExtra(EXTRA_DRINK_ID, id.toString())
                    )
                }
            }
        } catch (e: SQLiteException) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cursor.close()
        starbuzzDatabaseHelper.close()
    }
}
