package com.agapovp.android.headfirst1701

import android.content.ContentValues
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drink.*

class DrinkActivity : AppCompatActivity() {

    private var readDrinkTask: ReadDrinkTask? = null
    private var updateDrinkTask: UpdateDrinkTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        readDrinkTask = ReadDrinkTask().apply { execute(intent.extras?.getString(EXTRA_DRINK_ID)) }
    }

    override fun onDestroy() {
        super.onDestroy()

        updateDrinkTask?.cancel(false)
        updateDrinkTask = null
        readDrinkTask?.cancel(true)
        readDrinkTask = null
    }

    fun onFavoriteClicked(view: View) {
        updateDrinkTask =
            UpdateDrinkTask().apply { execute(intent.extras?.getString(EXTRA_DRINK_ID)) }
    }

    private inner class ReadDrinkTask : AsyncTask<String, Void, Drink?>() {

        override fun doInBackground(vararg drinks: String): Drink? {

            val starbuzzDatabaseHelper: SQLiteOpenHelper =
                StarbuzzDatabaseHelper(this@DrinkActivity)

            var drink: Drink? = null

            try {
                starbuzzDatabaseHelper
                    .readableDatabase
                    .query(
                        "DRINK",
                        arrayOf("NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVOURITE"),
                        "_id = ?",
                        arrayOf(drinks[0]),
                        null, null, null
                    ).use {
                        if (it.moveToFirst()) {
                            drink = Drink(
                                it.getString(0),
                                it.getString(1),
                                it.getInt(2),
                                it.getInt(3)
                            )
                        }
                    }
                return drink
            } catch (e: SQLiteException) {
                return drink
            } finally {
                starbuzzDatabaseHelper.close()
            }
        }

        override fun onPostExecute(drink: Drink?) {
            if (drink != null) {
                drink.let {
                    name.text = it.name
                    description.text = it.description
                    photo.run {
                        setImageResource(it.imageResourceId)
                        contentDescription = it.name
                    }
                    favorite.isChecked = (it.favourite == 1)
                }
            } else {
                Toast.makeText(this@DrinkActivity, "Database unavailable", Toast.LENGTH_SHORT)
                    .show()
            }
            readDrinkTask = null
        }
    }

    private inner class UpdateDrinkTask : AsyncTask<String, Void, Boolean>() {

        private lateinit var drinkValues: ContentValues

        override fun onPreExecute() {
            drinkValues = ContentValues().apply { put("FAVOURITE", favorite.isChecked) }
        }

        override fun doInBackground(vararg drinks: String): Boolean {

            val starbuzzDatabaseHelper = StarbuzzDatabaseHelper(this@DrinkActivity)

            return try {
                starbuzzDatabaseHelper.writableDatabase
                    .update(
                        "DRINK",
                        drinkValues,
                        "_id = ?",
                        arrayOf(drinks[0])
                    )
                true
            } catch (e: SQLiteException) {
                false
            } finally {
                starbuzzDatabaseHelper.close()
            }
        }

        override fun onPostExecute(success: Boolean) {
            if (!success) {
                Toast.makeText(this@DrinkActivity, "Database unavailable", Toast.LENGTH_SHORT)
                    .show()
            }
            updateDrinkTask = null
        }
    }

    companion object {
        const val EXTRA_DRINK_ID = "drinkId"
    }
}
