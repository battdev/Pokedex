package com.battagliandrea.pokedex.domain.entity

data class PokemonEntity (
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val type: List<String> = listOf(),
    val stats: List<Stat> = listOf()
){
    data class Stat(
        val base: Int = 0,
        val name: String = ""
    )
}