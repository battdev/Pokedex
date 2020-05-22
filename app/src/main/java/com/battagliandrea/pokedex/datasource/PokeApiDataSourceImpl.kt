package com.battagliandrea.pokedex.datasource

import com.battagliandrea.pokedex.data.PokeApiDataSource
import com.battagliandrea.pokedex.data.transform
import com.battagliandrea.pokedex.domain.PokemonEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokeApiDataSourceImpl @Inject constructor(
    private val pokeApiContract: PokeApiContract
) : PokeApiDataSource {

    override suspend fun getPokemon(offset: Int, limit: Int): List<PokemonEntity> {
        return pokeApiContract.getPokemon(offset = offset, limit = limit)
            .let {
                it.body()
                    ?.transform()
                    .orEmpty()
            }
    }
}