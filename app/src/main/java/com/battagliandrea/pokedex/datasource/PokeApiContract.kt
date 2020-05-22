package com.battagliandrea.pokedex.datasource

import com.battagliandrea.pokedex.data.GetPokemonData
import retrofit2.Response
import retrofit2.http.*

interface PokeApiContract {

    @GET("v2/pokemon")
    suspend fun getPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<GetPokemonData>
}