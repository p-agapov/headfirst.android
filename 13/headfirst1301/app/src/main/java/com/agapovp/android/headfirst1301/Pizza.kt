package com.agapovp.android.headfirst1301

class Pizza private constructor(
    val name: String,
    val imageResourceId: Int
) {
    companion object {
        val pizzas = arrayOf(
            Pizza("Diavolo", R.drawable.im_diavolo),
            Pizza("Funghi", R.drawable.im_funghi)
        )
    }
}
