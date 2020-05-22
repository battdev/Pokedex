package com.battagliandrea.pokedex.di.module

import com.battagliandrea.pokedex.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
open class FrameworkModule {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          GSON
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          OKHTTP
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          RETROFIT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Provides
//    @Singleton
//    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) =
//            createRetrofit(gson, okHttpClient, "your api")


//    @Provides
//    @Singleton
//    open fun provideApi(retrofit: Retrofit, context: Context): GithubContentContract = retrofit.create(GithubContentContract::class.java)
//
//
//    private fun createRetrofit(gson: Gson,
//                               okHttpClient: OkHttpClient,
//                               endpoint: String): Retrofit {
//
//        return Retrofit.Builder()
//                .baseUrl(endpoint)
//                .client(okHttpClient)
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build()
//    }
}
