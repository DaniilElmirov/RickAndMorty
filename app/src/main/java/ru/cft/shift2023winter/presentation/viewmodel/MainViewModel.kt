package ru.cft.shift2023winter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.usecases.GetCharactersUseCase
import ru.cft.shift2023winter.presentation.state.UiState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _state.value = UiState.Loading

            try {
                val characterList = getCharactersUseCase()
                _state.value = UiState.Content(characterList)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (ex: Exception) {
                _state.value = UiState.Error(ex.message)
            }
        }
    }
}