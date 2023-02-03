package ru.cft.shift2023winter.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.cft.shift2023winter.data.network.model.CharacterResponseDto
import ru.cft.shift2023winter.data.network.model.EpisodeResponseDto
import ru.cft.shift2023winter.data.network.model.LocationResponseDto

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacterResponse(@Query("page")page: Int): CharacterResponseDto

    @GET("episode")
    suspend fun getEpisodeResponse(): EpisodeResponseDto

    @GET("location")
    suspend fun getLocationResponse(): LocationResponseDto
}