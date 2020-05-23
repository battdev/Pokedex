package com.battagliandrea.pokedex.datasource

import com.battagliandrea.pokedex.data.datasource.PokeApiDataSource
import com.battagliandrea.pokedex.data.models.map
import com.battagliandrea.pokedex.data.models.transform
import com.battagliandrea.pokedex.domain.entity.PokemonEntity
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

    override suspend fun getPokemon(id: Int): PokemonEntity {
        return pokeApiContract.getPokemon(id = id)
            .let {
                it.body()!!.transform()
            }
    }
}