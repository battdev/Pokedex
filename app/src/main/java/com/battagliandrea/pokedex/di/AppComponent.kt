package com.battagliandrea.pokedex.di

import android.app.Application
import com.battagliandrea.pokedex.di.module.*
import com.battagliandrea.pokedex.di.viewmodel.BuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            ActivityModule::class,
            FragmentModule::class,
            BuilderModule::class,
            RepositoryModule::class,
            DataSourceModule::class,
            FrameworkModule::class,
            AndroidSupportInjectionModule::class
        ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun frameworkModule(module: FrameworkModule): Builder

        fun build(): AppComponent
    }

}
