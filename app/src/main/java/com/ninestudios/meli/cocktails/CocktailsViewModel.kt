package com.ninestudios.meli.cocktails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninestudios.meli.core.network.ApiStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CocktailsViewModel(private val repository: CocktailsRepository):ViewModel() {
    private val _cocktailsState : MutableStateFlow<CocktailsState> = MutableStateFlow(CocktailsState())
    var cocktailsState = _cocktailsState.asStateFlow()
        private set

    fun getData(name:String){
        viewModelScope.launch {
            repository.getCocktailsByName(name).collect{
                when(it.status) {
                    ApiStatus.SUCCESS -> {
                        _cocktailsState.value = _cocktailsState.value.copy(status = it.status, it.data)
                    }
                    ApiStatus.ERROR -> {
                        _cocktailsState.value = _cocktailsState.value.copy(status = it.status, messageError = it.message?:"")
                    }
                    else -> {
                        _cocktailsState.value = _cocktailsState.value.copy(status = it.status)
                    }
                }
            }
        }
    }


    data class CocktailsState(val status: ApiStatus = ApiStatus.SUCCESS, val data: CocktailsResponse? = CocktailsResponse(emptyList()), val messageError:String = "")
}