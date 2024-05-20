package com.battagliandrea.pokedex.data.datasource

import com.battagliandrea.pokedex.domain.entity.PokemonEntity


interface PokeApiDataSource {

    suspend fun getPokemon(offset: Int, limit: Int): List<PokemonEntity>

    suspend fun getPokemon(id: Int): PokemonEntity
}