package com.atb.pokedex.di

import com.atb.pokedex.data.remote.PokemonApi
import com.atb.pokedex.data.repository.PokemonRepositoryImpl
import com.atb.pokedex.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PokemonApi.BASE_URL)
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }
}