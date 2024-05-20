package com.battagliandrea.pokedex.usecase

import com.battagliandrea.pokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
        private val pokemonRepository: PokemonRepository
){
    suspend operator fun invoke() = pokemonRepository.get()
}


