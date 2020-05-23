package com.battagliandrea.pokedex.domain.repository

import com.battagliandrea.pokedex.domain.entity.PokemonEntity

interface PokemonRepository {

    suspend fun get(): List<PokemonEntity>

    suspend fun get(id: Int): PokemonEntity
}
