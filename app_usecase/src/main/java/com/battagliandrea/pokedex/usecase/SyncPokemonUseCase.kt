package com.battagliandrea.pokedex.usecase

import com.battagliandrea.pokedex.domain.PokemonRepository
import javax.inject.Inject

class SyncPokemonUseCase @Inject constructor(
        private val pokemonRepository: PokemonRepository
){

    suspend operator fun invoke() = pokemonRepository.sync()
}


