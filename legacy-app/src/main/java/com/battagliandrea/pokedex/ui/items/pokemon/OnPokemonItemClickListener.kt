package com.battagliandrea.pokedex.ui.items.pokemon

import android.view.View

interface OnPokemonItemClickListener {
    fun onItemClick(view: View, pokemonId: Int)
}