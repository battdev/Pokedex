package com.battagliandrea.pokedex.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.battagliandrea.pokedex.ui.items.base.ListItem
import com.battagliandrea.pokedex.ui.items.pokemon.PokemonItem

class  PokemonAdapterDiffUtils(
        private val oldList: List<ListItem>,
        private val newList: List<ListItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val oldItem = oldList[oldPosition]
        val newItem = newList[newPosition]
        return oldItem.id == newItem.id
                && oldItem is PokemonItem
                && newItem is PokemonItem
                && oldItem.name == newItem.name
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}