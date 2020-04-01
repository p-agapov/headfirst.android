package com.agapovp.android.headfirst0201

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_find_beer.*

class FindBeerActivity : AppCompatActivity() {

    private var expert: BeerExpert? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_beer)
    }

    override fun onResume() {
        super.onResume()
        expert = BeerExpert()
    }

    override fun onStop() {
        super.onStop()
        expert = null
    }

    fun onClickFindBeer(view: View) {

        val brandsList = expert?.getBrands(color.selectedItem.toString())
        val brandsFormatted = StringBuilder()

        for (brand in brandsList.orEmpty()) {
            brandsFormatted.append("$brand\n")
        }

        brands.text = brandsFormatted
    }
}
