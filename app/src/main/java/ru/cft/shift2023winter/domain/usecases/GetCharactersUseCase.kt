package ru.cft.shift2023winter.domain.usecases

import ru.cft.shift2023winter.domain.entities.Character
import ru.cft.shift2023winter.domain.repositories.RickAndMortyCharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val rickAndMortyCharacterRepository: RickAndMortyCharacterRepository) {

    suspend operator fun invoke(page: Int): List<Character> =
        rickAndMortyCharacterRepository.getCharacterList(page)
}