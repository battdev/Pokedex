package com.battagliandrea.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.pokedex.R
import com.battagliandrea.pokedex.ui.items.base.ListItem
import com.battagliandrea.pokedex.ui.items.type.TypeItem
import com.battagliandrea.pokedex.ui.items.type.TypeItemVH
import java.lang.RuntimeException
import javax.inject.Inject


open class TypeAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_POKETYPE = 0
    }

    private var data: MutableList<ListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_POKETYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_type_item, parent, false)
                TypeItemVH(view)
            }
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_POKETYPE -> (holder as TypeItemVH).render(data[position] as TypeItem)
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
            is TypeItem -> TYPE_POKETYPE
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