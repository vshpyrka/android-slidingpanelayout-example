package com.example.slidingpanelayout

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class Item(
    val name: String,
    val drawable: Int,
)

class SlidingPaneAdapter(
    private val onClick: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = listOf(
        Item("Marigold", R.drawable.ic_marigold),
        Item("Peony", R.drawable.ic_peony),
        Item("Poppy", R.drawable.ic_poppy),
        Item("Rose", R.drawable.ic_rose),
        Item("Sunflower", R.drawable.ic_sunflower),
        Item("Tulip", R.drawable.ic_tulip),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sliding_panel_layout, parent, false)
        return object : RecyclerView.ViewHolder(view) {
            init {
                view.setOnClickListener {
                    onClick()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        val image = holder.itemView.findViewById<ImageView>(R.id.image)
        image.setImageResource(item.drawable)
        val textView = holder.itemView.findViewById<TextView>(R.id.text)
        textView.text = item.name
    }

    override fun getItemCount() = data.size

}
