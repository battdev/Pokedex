package com.battagliandrea.pokedex.ui.details

import androidx.lifecycle.*
import com.battagliandrea.pokedex.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.pokedex.ui.base.ViewState
import com.battagliandrea.pokedex.ui.items.stat.toStatItem
import com.battagliandrea.pokedex.ui.items.title.toTypeItem
import com.battagliandrea.pokedex.usecase.GetPokemonDetailsUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*

open class DetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : ViewModel()
{

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<DetailsViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): DetailsViewModel
    }

    var pokeId: Int = 0
        set(value) {
            field = value
            load()
        }

    private val _header = MutableLiveData<DetailsViewState.Header>()
    val header: LiveData<DetailsViewState.Header> = _header

    private val _body = MutableLiveData<DetailsViewState.Body>()
    val body: LiveData<DetailsViewState.Body> = _body

    private fun load(){
        viewModelScope.launch {
            try{
                val pokemon = withContext(Dispatchers.Default) { getPokemonDetailsUseCase(pokeId) }
                _header.value = DetailsViewState.Header(dataViewState = ViewState.Success(data = pokemon))

                _body.value = DetailsViewState.Body(
                    typeViewState = ViewState.Success(data = pokemon.toTypeItem()),
                    statsViewState = ViewState.Success(data = pokemon.toStatItem())
                )
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
