package com.battagliandrea.pokedex.ui.items.stat

import com.battagliandrea.pokedex.domain.entity.PokemonEntity
import com.battagliandrea.pokedex.ui.items.base.ListItem


data class StatItem(
        override val id: Int = 0,
        val name: String = "",
        val value: Int = 0
): ListItem()


fun PokemonEntity.toStatItem(): List<ListItem>{
        return this.stats.map { stat ->
                StatItem(
                        name = stat.name,
                        value = stat.base
                )
        }
}
