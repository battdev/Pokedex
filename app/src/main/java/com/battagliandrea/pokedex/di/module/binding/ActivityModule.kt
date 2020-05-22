package com.battagliandrea.pokedex.di.module.binding

import com.battagliandrea.pokedex.di.scope.ActivityScope
import com.battagliandrea.pokedex.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}
