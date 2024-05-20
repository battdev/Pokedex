package com.battagliandrea.pokedex.data.repository


import com.battagliandrea.pokedex.data.datasource.PokeApiDataSource
import com.battagliandrea.pokedex.domain.entity.PokemonEntity
import com.battagliandrea.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class PokemonRepositoryImpl @Inject constructor(
        private val pokeApiDataSource: PokeApiDataSource
) : PokemonRepository {

    private val cachedData: MutableList<PokemonEntity> = mutableListOf()

    override suspend fun get(): List<PokemonEntity> {
        return pokeApiDataSource.getPokemon(offset = cachedData.size, limit = 20)
            .let { pokemon ->
                cachedData.addAll(pokemon)
                return@let cachedData
            }
    }

    override suspend fun get(id: Int): PokemonEntity {
        return pokeApiDataSource.getPokemon(id= id)
    }
}
