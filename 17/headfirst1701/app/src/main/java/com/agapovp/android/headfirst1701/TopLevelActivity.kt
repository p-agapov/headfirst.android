package com.agapovp.android.headfirst1701

import android.content.Intent
import android.database.Cursor
import android.database.SQLException
import android.os.AsyncTask
import android.os.Bundle
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agapovp.android.headfirst1701.DrinkActivity.Companion.EXTRA_DRINK_ID
import kotlinx.android.synthetic.main.activity_top_level.*

class TopLevelActivity : AppCompatActivity() {

    private var readFavouritesTask: ReadFavouritesTask? = null
    private var updateFavouritesTask: UpdateFavouritesTask? = null
    private var starbuzzDatabaseHelper: StarbuzzDatabaseHelper? = null
    private var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)

        setupOptionsListView()
        readFavouritesTask = ReadFavouritesTask().apply { execute() }
    }

    override fun onRestart() {
        super.onRestart()

        updateFavouritesTask = UpdateFavouritesTask().apply { execute() }
    }

    private fun setupOptionsListView() {
        list_options.setOnItemClickListener { _, _, position, _ ->
            if (position == 0) {
                startActivity(
                    Intent(this, DrinkCategoryActivity::class.java)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        readFavouritesTask?.cancel(true)
        readFavouritesTask = null
        updateFavouritesTask?.cancel(true)
        updateFavouritesTask = null
        cursor?.close()
        cursor = null
        starbuzzDatabaseHelper?.close()
        starbuzzDatabaseHelper = null
    }

    private inner class ReadFavouritesTask : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void?): Boolean {

            starbuzzDatabaseHelper = StarbuzzDatabaseHelper(this@TopLevelActivity)

            return try {
                cursor = starbuzzDatabaseHelper?.readableDatabase?.query(
                    "DRINK",
                    arrayOf("_id", "NAME"),
                    "FAVOURITE = 1",
                    null, null, null, null
                )
                true
            } catch (e: SQLException) {
                false
            }
        }

        override fun onPostExecute(success: Boolean) {
            if (success) {
                list_favourites.adapter = SimpleCursorAdapter(
                    this@TopLevelActivity,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    arrayOf("NAME"),
                    intArrayOf(android.R.id.text1),
                    0
                )

                list_favourites.setOnItemClickListener { _, _, _, id ->
                    startActivity(
                        Intent(this@TopLevelActivity, DrinkActivity::class.java)
                            .putExtra(EXTRA_DRINK_ID, id.toString())
                    )
                }
            } else {
                Toast.makeText(this@TopLevelActivity, "Database unavailable", Toast.LENGTH_SHORT)
                    .show()
            }
            readFavouritesTask = null
        }
    }

    private inner class UpdateFavouritesTask : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void?): Boolean {

            return try {
                cursor = starbuzzDatabaseHelper?.readableDatabase?.query(
                    "DRINK",
                    arrayOf("_id", "NAME"),
                    "FAVOURITE = 1",
                    null, null, null, null
                )
                true
            } catch (e: SQLException) {
                false
            }
        }

        override fun onPostExecute(success: Boolean) {
            if (success) {
                (list_favourites.adapter as CursorAdapter).changeCursor(cursor)
            } else {
                Toast.makeText(this@TopLevelActivity, "Database unavailable", Toast.LENGTH_SHORT)
                    .show()
            }
            updateFavouritesTask = null
        }
    }
}
