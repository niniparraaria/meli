package com.ninestudios.meli.cocktail_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninestudios.meli.cocktails.CocktailsResponse
import com.ninestudios.meli.core.network.ApiStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CocktailDetailViewModel(private val repository: CocktailDetailRepository):ViewModel() {
    private val _cocktailDetailState : MutableStateFlow<CocktailDetailState> = MutableStateFlow(CocktailDetailState())
    var cocktailDetailState = _cocktailDetailState.asStateFlow()
        private set

    fun getData(id:String){
        viewModelScope.launch {
            repository.getCocktailsById(id).collect{
                when(it.status) {
                    ApiStatus.SUCCESS -> {
                        _cocktailDetailState.value = _cocktailDetailState.value.copy(status = it.status, it.data)
                    }
                    ApiStatus.ERROR -> {
                        _cocktailDetailState.value = _cocktailDetailState.value.copy(status = it.status, messageError = it.message?:"")
                    }
                    else -> {
                        _cocktailDetailState.value = _cocktailDetailState.value.copy(status = it.status)
                    }
                }
            }
        }
    }


    data class CocktailDetailState(val status: ApiStatus = ApiStatus.LOADING, val data: CocktailsResponse? = null, val messageError:String = "")
}