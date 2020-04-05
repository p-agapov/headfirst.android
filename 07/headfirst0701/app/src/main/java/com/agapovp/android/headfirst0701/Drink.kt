package com.agapovp.android.headfirst0701

class Drink constructor(
    val name: String,
    val description: String,
    val imageResourceId: Int
) {

    override fun toString(): String {
        return this.name
    }

    companion object {
        val drinks = arrayOf(
            Drink(
                "Latte",
                "A couple of espresso shots with steamed milk",
                R.drawable.latte
            ),
            Drink(
                "Cappuccino",
                "Espresso, hot milk, and a steamed foam",
                R.drawable.cappuccino
            ),
            Drink(
                "Filter",
                "Highest quality beans roasted and brewed fresh",
                R.drawable.filter
            )
        )
    }
}
