package com.battagliandrea.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.pokedex.R
import com.battagliandrea.pokedex.ui.items.base.ListItem
import com.battagliandrea.pokedex.ui.items.pokemon.OnPokemonItemClickListener
import com.battagliandrea.pokedex.ui.items.pokemon.PokemonItem
import com.battagliandrea.pokedex.ui.items.pokemon.PokemonItemVH
import com.battagliandrea.pokedex.ui.items.title.TitleItem
import com.battagliandrea.pokedex.ui.items.title.TitleItemVH
import java.lang.RuntimeException
import javax.inject.Inject


open class PokemonAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_TITLE = 0
        const val TYPE_POKEMON = 1
    }

    var onItemClickListener: OnPokemonItemClickListener? = null

    private var data: MutableList<ListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_TITLE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_title_item, parent, false)
                TitleItemVH(view)
            }
            TYPE_POKEMON -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pokemon_item, parent, false)
                PokemonItemVH(view)
            }
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_TITLE -> (holder as TitleItemVH).render(data[position] as TitleItem)
            TYPE_POKEMON -> (holder as PokemonItemVH).render(data[position] as PokemonItem, onItemClickListener)
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is TitleItem -> TYPE_TITLE
            is PokemonItem -> TYPE_POKEMON
            else -> -1
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UTILS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun updateList(data: List<ListItem>){
        if(this.data != data){
            val diffCallback = PokemonAdapterDiffUtils(this.data, data)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            this.data.clear()
            this.data.addAll(data)
            diffResult.dispatchUpdatesTo(this)
        }
    }
}