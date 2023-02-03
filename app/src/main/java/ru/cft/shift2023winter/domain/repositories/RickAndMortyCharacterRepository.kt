package ru.cft.shift2023winter.domain.repositories

import ru.cft.shift2023winter.domain.entities.Character

interface RickAndMortyCharacterRepository {

    suspend fun getCharacterList(page: Int): List<Character>
}