package com.battagliandrea.pokedex.ui.items.type

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_type_item.view.*

class TypeItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: TypeItem) = with(itemView) {
        tvType.text = item.name.capitalize()
    }
}