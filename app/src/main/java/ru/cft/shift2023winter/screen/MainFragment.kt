package ru.cft.shift2023winter.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.databinding.FragmentMainBinding
import ru.cft.shift2023winter.domain.entities.Character
import ru.cft.shift2023winter.presentation.RickAndMortyApplication
import ru.cft.shift2023winter.presentation.state.UiState
import ru.cft.shift2023winter.presentation.viewmodel.MainViewModel
import ru.cft.shift2023winter.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val characterListAdapter = CharacterListAdapter {
        launchCharacterDetailsFragment(it)
    }

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as RickAndMortyApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.rvCharacterList.layoutManager = StaggeredGridLayoutManager(3, 1)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.rvCharacterList.adapter = characterListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.state.collect {
                    when (it) {
                        UiState.Initial -> Unit
                        UiState.Loading -> Unit
                        is UiState.Content -> {
                            characterListAdapter.characterList = it.characterList
                        }
                        is UiState.Error -> Unit
                    }
                }
            }
        }
        mainViewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchCharacterDetailsFragment(character: Character) {
        findNavController().navigate(
            MainFragmentDirections.showCharacterDetail(character)
        )
    }
}