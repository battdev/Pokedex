package com.battagliandrea.pokedex.data


import com.battagliandrea.pokedex.domain.PokemonEntity
import com.battagliandrea.pokedex.domain.PokemonRepository
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
        TODO("Not yet implemented")
    }
}
