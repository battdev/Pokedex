package com.battagliandrea.pokedex.datasource

import com.battagliandrea.pokedex.data.models.GetPokemonData
import com.battagliandrea.pokedex.data.models.PokemonData
import retrofit2.Response
import retrofit2.http.*

interface PokeApiContract {

    @GET("v2/pokemon")
    suspend fun getPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<GetPokemonData>

    @GET("v2/pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Response<PokemonData>
}