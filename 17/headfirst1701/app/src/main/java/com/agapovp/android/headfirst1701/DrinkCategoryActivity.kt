package com.agapovp.android.headfirst1701

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.os.AsyncTask
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agapovp.android.headfirst1701.DrinkActivity.Companion.EXTRA_DRINK_ID
import kotlinx.android.synthetic.main.activity_drink_category.*

class DrinkCategoryActivity : AppCompatActivity() {

    private var readDrinksTask: ReadDrinksTask? = null
    private var starbuzzDatabaseHelper: StarbuzzDatabaseHelper? = null
    private var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        readDrinksTask = ReadDrinksTask().apply { execute() }
    }

    override fun onDestroy() {
        super.onDestroy()
        readDrinksTask?.cancel(true)
        readDrinksTask = null
        cursor?.close()
        cursor = null
        starbuzzDatabaseHelper?.close()
        starbuzzDatabaseHelper = null
    }

    private inner class ReadDrinksTask : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void?): Boolean {

            starbuzzDatabaseHelper = StarbuzzDatabaseHelper(this@DrinkCategoryActivity)

            return try {
                cursor = starbuzzDatabaseHelper?.readableDatabase?.query(
                    "DRINK",
                    arrayOf("_id", "NAME"),
                    null, null, null, null, null
                )
                true
            } catch (e: SQLiteException) {
                false
            }
        }

        override fun onPostExecute(success: Boolean) {
            if (success) {
                list_drinks.run {
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
            } else {
                Toast.makeText(this@DrinkCategoryActivity, "Database unavailable", Toast.LENGTH_SHORT)
                    .show()
            }
            readDrinksTask = null
        }
    }
}
