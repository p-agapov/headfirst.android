package com.agapovp.android.headfirst1301

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CaptionedImagesAdapter(
    private val captions: Array<String?>,
    private val imageIds: IntArray,
    private val action: (Int) -> Unit
) : RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>() {

    override fun getItemCount() = captions.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_captioned_image, parent, false) as CardView
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardView.run {
            findViewById<ImageView>(R.id.card_view_image).run {
                setImageDrawable(ContextCompat.getDrawable(this@run.context, imageIds[position]))
                contentDescription = captions[position]
            }
            findViewById<TextView>(R.id.card_view_text).text = captions[position]
            setOnClickListener {
                action.invoke(position)
            }
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)
}
