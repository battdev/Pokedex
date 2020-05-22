package com.battagliandrea.pokedex.di.module.binding

import com.battagliandrea.pokedex.di.scope.FragmentScope
import com.battagliandrea.pokedex.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment
}
