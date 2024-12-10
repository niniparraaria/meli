package com.ninestudios.meli.core.network

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getCocktailsByName(name:String) = apiService.getCocktail(name)
    suspend fun getCocktailsByID(id:String) = apiService.getCocktailDetail(id)
}