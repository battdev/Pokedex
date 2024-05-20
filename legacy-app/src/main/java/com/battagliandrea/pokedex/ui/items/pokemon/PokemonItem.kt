package com.battagliandrea.pokedex.ui.items.pokemon

import com.battagliandrea.pokedex.BuildConfig
import com.battagliandrea.pokedex.domain.entity.PokemonEntity
import com.battagliandrea.pokedex.ui.items.base.ListItem
import com.battagliandrea.pokedex.ui.items.stat.StatItem

data class PokemonItem (
    override var id : Int = 0,
    var name : String = String(),
    var image: String = String()
) : ListItem()

fun List<PokemonEntity>.toPokemonItems(): MutableList<ListItem>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.toPokemonItem() }
        .toMutableList()
}

fun PokemonEntity.toPokemonItem(): ListItem{
    return PokemonItem(
        id = this.id,
        name = this.name,
        image = "${BuildConfig.apiResUrl}${this.id}.png"
    )
}