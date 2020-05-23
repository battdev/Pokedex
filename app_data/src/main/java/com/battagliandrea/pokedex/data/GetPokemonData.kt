package com.battagliandrea.pokedex.data


import com.battagliandrea.pokedex.domain.PokemonEntity
import com.google.gson.annotations.SerializedName

data class GetPokemonData(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String = "",
    @SerializedName("previous")
    val previous: String = "",
    @SerializedName("results")
    val results: List<Result> = listOf()
) {
    data class Result(
        @SerializedName("name")
        val name: String = "",
        @SerializedName("url")
        val url: String = ""
    )
}

fun GetPokemonData.transform(): List<PokemonEntity>{
    return this.results
        .asSequence()
        .filterNotNull()
        .map { it.map() }
        .toList()
}

fun GetPokemonData.Result.map(): PokemonEntity{
    var id = 0
    try{
        id = this.url.substring(0, this.url.length - 1).split("/").last().toInt()
    } catch (e: Exception){
        e.printStackTrace()
    }

    return PokemonEntity(
        id = id,
        name = this.name,
        image = id.toString()
    )
}

