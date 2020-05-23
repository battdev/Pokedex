package com.battagliandrea.pokedex.ui.items.pokemon

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_pokemon_item.view.*

class PokemonItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: PokemonItem, listener: OnPokemonItemClickListener? = null) = with(itemView) {

        ivAvatar.transitionName = "${item.id}"

        tvName.text = item.name.capitalize()
        tvId.text = String.format("#%03d", item.id)

        Glide.with(this)
            .load(item.image)
            .apply(RequestOptions()
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .dontAnimate())
            .into(ivAvatar)



        itemView.setOnClickListener {
            listener?.onItemClick(ivAvatar, item.id)
        }
    }
}