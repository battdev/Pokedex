package com.battagliandrea.pokedex.domain

import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun observe(): Flow<List<PokemonEntity>>

    suspend fun sync()

    suspend fun get(id: Int): PokemonEntity
}
