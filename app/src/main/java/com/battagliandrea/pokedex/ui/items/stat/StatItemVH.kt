package com.battagliandrea.pokedex.ui.items.stat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_stat_item.view.*

class StatItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: StatItem) = with(itemView) {

        tvName.text = item.name.capitalize()
        tvValue.text ="${item.value}"

        progressBar.progress = item.value
    }
}