package com.battagliandrea.pokedex.usecase

import com.battagliandrea.pokedex.domain.PokemonEntity
import com.battagliandrea.pokedex.domain.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ObservePokemonUseCase @Inject constructor(
        private val pokemonRepository: PokemonRepository
){

    @ExperimentalCoroutinesApi
    suspend operator fun invoke() : Flow<List<PokemonEntity>>{
            return pokemonRepository.observe()
    }
}


