package com.battagliandrea.pokedex.ui.details

import com.battagliandrea.pokedex.domain.entity.PokemonEntity
import com.battagliandrea.pokedex.ui.base.ViewState
import com.battagliandrea.pokedex.ui.items.base.ListItem

sealed class DetailsViewState {

    data class Header(
        val pokemonDataViewState: ViewState<PokemonEntity>
    )
}


