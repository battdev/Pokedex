package com.battagliandrea.pokedex.ui.details

import com.battagliandrea.pokedex.domain.entity.PokemonEntity
import com.battagliandrea.pokedex.ui.base.ViewState
import com.battagliandrea.pokedex.ui.items.base.ListItem
import com.battagliandrea.pokedex.ui.items.stat.StatItem

sealed class DetailsViewState {

    data class Header(
        val dataViewState: ViewState<PokemonEntity>
    )

    data class Body(
        val typeViewState: ViewState<List<ListItem>>,
        val statsViewState: ViewState<List<ListItem>>
    )

}


