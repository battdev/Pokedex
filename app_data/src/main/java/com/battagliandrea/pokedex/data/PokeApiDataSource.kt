package com.battagliandrea.pokedex.data

import com.battagliandrea.pokedex.domain.PokemonEntity


interface PokeApiDataSource {

    suspend fun getPokemon(offset: Int, limit: Int): List<PokemonEntity>
}