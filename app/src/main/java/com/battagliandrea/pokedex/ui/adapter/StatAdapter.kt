package com.battagliandrea.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.pokedex.R
import com.battagliandrea.pokedex.ui.items.base.ListItem
import com.battagliandrea.pokedex.ui.items.loading.LoadingItem
import com.battagliandrea.pokedex.ui.items.loading.LoadingItemVH
import com.battagliandrea.pokedex.ui.items.pokemon.OnPokemonItemClickListener
import com.battagliandrea.pokedex.ui.items.pokemon.PokemonItem
import com.battagliandrea.pokedex.ui.items.pokemon.PokemonItemVH
import com.battagliandrea.pokedex.ui.items.stat.StatItem
import com.battagliandrea.pokedex.ui.items.stat.StatItemVH
import com.battagliandrea.pokedex.ui.items.title.TitleItem
import com.battagliandrea.pokedex.ui.items.title.TitleItemVH
import java.lang.RuntimeException
import java.util.*
import javax.inject.Inject


open class StatAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_STAT = 0
    }

    private var data: MutableList<ListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_STAT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_stat_item, parent, false)
                StatItemVH(view)
            }
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_STAT -> (holder as StatItemVH).render(data[position] as StatItem)
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is StatItem -> TYPE_STAT
            else -> -1
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UTILS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun updateList(data: List<ListItem>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}