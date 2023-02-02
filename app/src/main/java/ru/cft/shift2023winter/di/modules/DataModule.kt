package ru.cft.shift2023winter.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import ru.cft.shift2023winter.data.network.api.RickAndMortyApi
import ru.cft.shift2023winter.data.repositories.RickAndMortyCharacterRepositoryImpl
import ru.cft.shift2023winter.data.repositories.RickAndMortyEpisodeRepositoryImpl
import ru.cft.shift2023winter.data.repositories.RickAndMortyLocationRepositoryImpl
import ru.cft.shift2023winter.di.annotations.ApplicationScope
import ru.cft.shift2023winter.domain.repositories.RickAndMortyCharacterRepository
import ru.cft.shift2023winter.domain.repositories.RickAndMortyEpisodeRepository
import ru.cft.shift2023winter.domain.repositories.RickAndMortyLocationRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRickAndMortyCharacterRepository(impl: RickAndMortyCharacterRepositoryImpl): RickAndMortyCharacterRepository

    @ApplicationScope
    @Binds
    fun bindRickAndMortyEpisodeRepository(impl: RickAndMortyEpisodeRepositoryImpl): RickAndMortyEpisodeRepository

    @ApplicationScope
    @Binds
    fun bindRickAndMortyLocationRepository(impl: RickAndMortyLocationRepositoryImpl): RickAndMortyLocationRepository

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        @ApplicationScope
        @Provides
        fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()

        @ApplicationScope
        @Provides
        fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply{ level = HttpLoggingInterceptor.Level.BODY })
            .build()


        @ApplicationScope
        @Provides
        fun provideRickAndMortyApi(retrofit: Retrofit): RickAndMortyApi = retrofit.create()
    }
}