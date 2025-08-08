package com.cosanodev.pokeapp.data.di

import com.cosanodev.pokeapp.data.DataConstants.POKE_API_BASE_URL
import com.cosanodev.pokeapp.data.datasource.PokeApiService
import com.cosanodev.pokeapp.data.repository.PokemonRepositoryImpl
import com.cosanodev.pokeapp.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providePokemonRepository(api: PokeApiService): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }

    @Provides
    fun providePokeApiServices(retrofit: Retrofit): PokeApiService {
        return retrofit.create(PokeApiService::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(POKE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()
}