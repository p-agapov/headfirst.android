package com.agapovp.android.headfirst1601

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StarbuzzDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        updateDatabase(db, 0, DB_VERSION)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        updateDatabase(db, oldVersion, newVersion)
    }

    private fun updateDatabase(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 1) {
            db?.run {
                execSQL(CREATE_TABLE_DRINK_QUERY)
                insertDrink(
                    "Latte",
                    "Espresso and steamed milk",
                    R.drawable.latte
                )
                insertDrink(
                    "Cappuccino",
                    "Espresso, hot milk and a steamed-milk foam",
                    R.drawable.cappuccino
                )
                insertDrink(
                    "Filter",
                    "Our best drip coffee",
                    R.drawable.filter
                )
            }
        }
        if (oldVersion < 2) {
            db?.execSQL(DRINK_ADD_COLUMN_FAVORITE_QUERY)
        }
    }

    companion object {
        const val DB_NAME = "starbuzz"
        const val DB_VERSION = 2

        const val CREATE_TABLE_DRINK_QUERY =
            """CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT,
            NAME TEXT,
            DESCRIPTION TEXT,
            IMAGE_RESOURCE_ID INTEGER);
        """

        const val DRINK_ADD_COLUMN_FAVORITE_QUERY = "ALTER TABLE DRINK ADD COLUMN FAVOURITE NUMERIC;"

        private fun SQLiteDatabase.insertDrink(name: String, description: String, resourceId: Int) {
            ContentValues().run {
                put("NAME", name)
                put("DESCRIPTION", description)
                put("IMAGE_RESOURCE_ID", resourceId)
                this@insertDrink.insert("DRINK", null, this)
            }
        }
    }
}
