package com.battagliandrea.pokedex.di.module.data


import com.battagliandrea.pokedex.data.PokeApiDataSource
import com.battagliandrea.pokedex.datasource.PokeApiDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provide steps-level dependencies.
 */
@Module
open class DataSourceModule {

    @Provides
    @Singleton
    fun providePokeApiDataSource(ds: PokeApiDataSourceImpl): PokeApiDataSource = ds

}
