package com.battagliandrea.pokedex.ui.main

import android.content.Context
import androidx.lifecycle.*
import com.battagliandrea.pokedex.R
import com.battagliandrea.pokedex.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.pokedex.ui.base.SingleLiveEvent
import com.battagliandrea.pokedex.ui.base.ViewState
import com.battagliandrea.pokedex.ui.items.pokemon.toPokemonItems
import com.battagliandrea.pokedex.ui.items.title.TitleItem
import com.battagliandrea.pokedex.usecase.GetPokemonUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*

open class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val context: Context,
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel()
{

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    private val _listViewState = SingleLiveEvent<MainViewState.PokemonList>()
    val listViewState: LiveData<MainViewState.PokemonList> = _listViewState

    init {
        _listViewState.value = MainViewState.PokemonList(listViewState = ViewState.Loading())

        load()
    }

    fun load(){
        if(isLoading.not()){
            viewModelScope.launch {
                try{

                    isLoading = true
                    _listViewState.value = _listViewState.value?.copy(listViewState = ViewState.Loading())

                    val pokemon = withContext(Dispatchers.Default) { getPokemonUseCase() }
                    val data = pokemon.toPokemonItems()
                    data.add(0, TitleItem(text = context.getString(R.string.pokedex)))

                    isLoading = false
                    _listViewState.value = _listViewState.value?.copy(listViewState = ViewState.Success(data = data))
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
