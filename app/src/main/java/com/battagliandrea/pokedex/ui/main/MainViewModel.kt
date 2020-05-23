package com.battagliandrea.pokedex.ui.main

import android.content.Context
import androidx.lifecycle.*
import com.battagliandrea.pokedex.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.pokedex.ui.base.ViewState
import com.battagliandrea.pokedex.ui.items.pokemon.toItems
import com.battagliandrea.pokedex.ui.items.title.TitleItem
import com.battagliandrea.pokedex.usecase.ObservePokemonUseCase
import com.battagliandrea.pokedex.usecase.SyncPokemonUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

open class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val context: Context,
    private val observePokemonUseCase: ObservePokemonUseCase,
    private val syncPokemonUseCase: SyncPokemonUseCase
) : ViewModel()
{

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }

    private val _listViewState = MutableLiveData<MainViewState.PokemonList>()
    val listViewState: LiveData<MainViewState.PokemonList> = _listViewState

    init {
        _listViewState.value = MainViewState.PokemonList(
            listViewState = ViewState.Loading(data = listOf())
        )

        observer()
        load()
    }

    private fun observer(){
        viewModelScope.launch {
            try {
                observePokemonUseCase().collect { pokemon ->

                    val data = pokemon.toItems()
                    data.add(0, TitleItem(text = "Pokedex"))

                    _listViewState.value = MainViewState.PokemonList(
                        listViewState = ViewState.Success(data = data)
                    )
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun load(){
        try{
            viewModelScope.launch {
                withContext(Dispatchers.Default) { syncPokemonUseCase() }
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
