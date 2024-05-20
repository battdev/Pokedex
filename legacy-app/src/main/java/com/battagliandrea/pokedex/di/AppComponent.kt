package com.battagliandrea.pokedex.di

import android.app.Application
import com.battagliandrea.pokedex.di.module.*
import com.battagliandrea.pokedex.di.module.binding.ActivityModule
import com.battagliandrea.pokedex.di.module.binding.FragmentModule
import com.battagliandrea.pokedex.di.module.data.DataSourceModule
import com.battagliandrea.pokedex.di.module.data.RepositoryModule
import com.battagliandrea.pokedex.di.module.framework.RetrofitModule
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
            UtilsModule::class,
            ActivityModule::class,
            FragmentModule::class,
            BuilderModule::class,
            RepositoryModule::class,
            DataSourceModule::class,
            RetrofitModule::class,
            AndroidSupportInjectionModule::class
        ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun frameworkModule(module: RetrofitModule): Builder

        fun build(): AppComponent
    }

}
