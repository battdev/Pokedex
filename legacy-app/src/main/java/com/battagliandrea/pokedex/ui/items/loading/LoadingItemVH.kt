package com.battagliandrea.pokedex.ui.items.loading

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.pokedex.R
import kotlinx.android.synthetic.main.view_loading_item.view.*


class LoadingItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: LoadingItem) = with(itemView) {
        val rotation: Animation = AnimationUtils.loadAnimation(itemView.context, R.anim.clockwise_rotation)
        rotation.repeatCount = Animation.INFINITE
        ivLoader.startAnimation(rotation)
    }
}