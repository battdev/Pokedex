package com.battagliandrea.pokedex.data.models


import com.battagliandrea.pokedex.domain.entity.PokemonEntity
import com.google.gson.annotations.SerializedName

data class PokemonData(

    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("weight")
    val weight: Int = 0,
    @SerializedName("stats")
    val stats: List<Stat> = listOf(),
    @SerializedName("types")
    val types: List<Type> = listOf()

) {

    data class Stat(
        @SerializedName("base_stat")
        val baseStat: Int = 0,
        @SerializedName("effort")
        val effort: Int = 0,
        @SerializedName("stat")
        val stat: Stat = Stat()
    ) {
        data class Stat(
            @SerializedName("name")
            val name: String = "",
            @SerializedName("url")
            val url: String = ""
        )
    }

    data class Type(
        @SerializedName("slot")
        val slot: Int = 0,
        @SerializedName("type")
        val type: Type = Type()
    ) {
        data class Type(
            @SerializedName("name")
            val name: String = "",
            @SerializedName("url")
            val url: String = ""
        )
    }
}

fun PokemonData.transform(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        image = this.id.toString(),
        type = this.types.map { it.type.name },
        stats = this.stats.map { PokemonEntity.Stat(base =  it.baseStat, name = it.stat.name) }
    )
}