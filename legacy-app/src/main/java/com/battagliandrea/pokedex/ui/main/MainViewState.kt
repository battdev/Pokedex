package com.battagliandrea.pokedex.ui.main

import com.battagliandrea.pokedex.ui.base.ViewState
import com.battagliandrea.pokedex.ui.items.base.ListItem

sealed class MainViewState {

    data class PokemonList(
        val listViewState: ViewState<List<ListItem>>
    )
}


