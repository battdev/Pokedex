package com.battagliandrea.pokedex.di.module.data


import com.battagliandrea.pokedex.data.repository.PokemonRepositoryImpl
import com.battagliandrea.pokedex.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(ds: PokemonRepositoryImpl): PokemonRepository = ds
}
