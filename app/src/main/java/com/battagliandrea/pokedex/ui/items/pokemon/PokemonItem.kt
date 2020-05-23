package com.battagliandrea.pokedex.ui.items.pokemon

import com.battagliandrea.pokedex.BuildConfig
import com.battagliandrea.pokedex.domain.PokemonEntity
import com.battagliandrea.pokedex.ui.items.base.ListItem

data class PokemonItem (
    override var id : Int = 0,
    var name : String = String(),
    var image: String = String()
) : ListItem()

fun List<PokemonEntity>.toItems(): MutableList<ListItem>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.toItemModel() }
        .toMutableList()
}

fun PokemonEntity.toItemModel(): ListItem{
    return PokemonItem(
        id = this.id,
        name = this.name,
        image = "${BuildConfig.apiResUrl}${this.id}.png"
    )
}