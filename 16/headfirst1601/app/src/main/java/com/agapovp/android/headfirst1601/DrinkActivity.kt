package com.agapovp.android.headfirst1601

import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drink.*

class DrinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val starbuzzDatabaseHelper: SQLiteOpenHelper = StarbuzzDatabaseHelper(this)

        try {
            starbuzzDatabaseHelper
                .readableDatabase
                .query(
                    "DRINK",
                    arrayOf("NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"),
                    "_id = ?",
                    arrayOf(intent.extras?.getString(EXTRA_DRINK_ID)),
                    null, null, null
                )
                .use {
                    if (it.moveToFirst()) {
                        name.text = it.getString(0)
                        description.text = it.getString(1)
                        photo.run {
                            setImageResource(it.getInt(2))
                            contentDescription = it.getString(0)
                        }
                    }
                }
        } catch (e: SQLiteException) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show()
        } finally {
            starbuzzDatabaseHelper.close()
        }
    }

    companion object {
        const val EXTRA_DRINK_ID = "drinkId"
    }
}
