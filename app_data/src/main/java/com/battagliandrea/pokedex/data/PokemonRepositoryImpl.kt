package com.battagliandrea.pokedex.data


import com.battagliandrea.pokedex.domain.PokemonEntity
import com.battagliandrea.pokedex.domain.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class PokemonRepositoryImpl @Inject constructor(
        private val pokeApiDataSource: PokeApiDataSource
) : PokemonRepository {

    @ExperimentalCoroutinesApi
    private val channel: ConflatedBroadcastChannel<List<PokemonEntity>> = ConflatedBroadcastChannel(listOf())

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun observe(): Flow<List<PokemonEntity>> {
        return channel.asFlow()
    }

    @ExperimentalCoroutinesApi
    override suspend fun sync() {
        pokeApiDataSource.getPokemon(offset = channel.value.size, limit = 20)
            .let { pokemon -> channel.send(pokemon) }
    }

    override suspend fun get(id: Int): PokemonEntity {
        TODO("Not yet implemented")
    }
}
