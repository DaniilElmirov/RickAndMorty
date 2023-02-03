package ru.cft.shift2023winter.data.repositories

import ru.cft.shift2023winter.data.mappers.CharacterDtoMapper
import ru.cft.shift2023winter.data.network.api.RickAndMortyApi
import ru.cft.shift2023winter.domain.entities.Character
import ru.cft.shift2023winter.domain.repositories.RickAndMortyCharacterRepository
import javax.inject.Inject

class RickAndMortyCharacterRepositoryImpl @Inject constructor(
    private val characterDtoMapper: CharacterDtoMapper,
    private val api: RickAndMortyApi,
) : RickAndMortyCharacterRepository {

    override suspend fun getCharacterList(page: Int): List<Character> =
        api.getCharacterResponse(page).results.map { characterDtoMapper(it) }
}