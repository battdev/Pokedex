package com.battagliandrea.pokedex.domain

interface PokemonRepository {

    suspend fun get(): List<PokemonEntity>

    suspend fun get(id: Int): PokemonEntity
}
