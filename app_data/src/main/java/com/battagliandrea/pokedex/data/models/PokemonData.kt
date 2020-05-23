package com.battagliandrea.pokedex.data.models


import com.battagliandrea.pokedex.domain.entity.PokemonEntity
import com.google.gson.annotations.SerializedName

data class PokemonData(

    @SerializedName("abilities")
    val abilities: List<Ability> = listOf(),
    @SerializedName("base_experience")
    val baseExperience: Int = 0,
    @SerializedName("forms")
    val forms: List<Form> = listOf(),
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice> = listOf(),
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("held_items")
    val heldItems: List<Any> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("is_default")
    val isDefault: Boolean = false,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String = "",
    @SerializedName("moves")
    val moves: List<Move> = listOf(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("order")
    val order: Int = 0,
    @SerializedName("species")
    val species: Species = Species(),
    @SerializedName("sprites")
    val sprites: Sprites = Sprites(),
    @SerializedName("stats")
    val stats: List<Stat> = listOf(),
    @SerializedName("types")
    val types: List<Type> = listOf(),
    @SerializedName("weight")
    val weight: Int = 0
) {
    data class Ability(
        @SerializedName("ability")
        val ability: Ability = Ability(),
        @SerializedName("is_hidden")
        val isHidden: Boolean = false,
        @SerializedName("slot")
        val slot: Int = 0
    ) {
        data class Ability(
            @SerializedName("name")
            val name: String = "",
            @SerializedName("url")
            val url: String = ""
        )
    }

    data class Form(
        @SerializedName("name")
        val name: String = "",
        @SerializedName("url")
        val url: String = ""
    )

    data class GameIndice(
        @SerializedName("game_index")
        val gameIndex: Int = 0,
        @SerializedName("version")
        val version: Version = Version()
    ) {
        data class Version(
            @SerializedName("name")
            val name: String = "",
            @SerializedName("url")
            val url: String = ""
        )
    }

    data class Move(
        @SerializedName("move")
        val move: Move = Move(),
        @SerializedName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail> = listOf()
    ) {
        data class Move(
            @SerializedName("name")
            val name: String = "",
            @SerializedName("url")
            val url: String = ""
        )

        data class VersionGroupDetail(
            @SerializedName("level_learned_at")
            val levelLearnedAt: Int = 0,
            @SerializedName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod = MoveLearnMethod(),
            @SerializedName("version_group")
            val versionGroup: VersionGroup = VersionGroup()
        ) {
            data class MoveLearnMethod(
                @SerializedName("name")
                val name: String = "",
                @SerializedName("url")
                val url: String = ""
            )

            data class VersionGroup(
                @SerializedName("name")
                val name: String = "",
                @SerializedName("url")
                val url: String = ""
            )
        }
    }

    data class Species(
        @SerializedName("name")
        val name: String = "",
        @SerializedName("url")
        val url: String = ""
    )

    data class Sprites(
        @SerializedName("back_default")
        val backDefault: String = "",
        @SerializedName("back_female")
        val backFemale: Any = Any(),
        @SerializedName("back_shiny")
        val backShiny: String = "",
        @SerializedName("back_shiny_female")
        val backShinyFemale: Any = Any(),
        @SerializedName("front_default")
        val frontDefault: String = "",
        @SerializedName("front_female")
        val frontFemale: Any = Any(),
        @SerializedName("front_shiny")
        val frontShiny: String = "",
        @SerializedName("front_shiny_female")
        val frontShinyFemale: Any = Any()
    )

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
        image = this.id.toString()
    )
}