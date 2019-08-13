package com.agapovp.android.headfirst0201

class BeerExpert {

    internal fun getBrands(color: String) = if (color == "amber") {
        listOf("Jack Amber", "Red Moose")
    } else {
        listOf("Jail Pale Ale", "Gout Stout")
    }
}
