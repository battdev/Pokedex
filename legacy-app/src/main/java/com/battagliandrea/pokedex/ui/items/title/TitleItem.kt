package com.battagliandrea.pokedex.ui.items.title

import com.battagliandrea.pokedex.domain.entity.PokemonEntity
import com.battagliandrea.pokedex.ui.items.base.ListItem
import com.battagliandrea.pokedex.ui.items.type.TypeItem


data class TitleItem(
        override val id: Int = 0,
        val text: String = ""
): ListItem()

fun PokemonEntity.toTypeItem(): List<ListItem>{
        return this.type.map { type ->
                TypeItem(
                        name = type
                )
        }
}