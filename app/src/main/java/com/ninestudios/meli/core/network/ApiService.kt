package com.ninestudios.meli.core.network

import com.ninestudios.meli.cocktails.CocktailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php")
    suspend fun getCocktail(@Query("s") name:String): Response<CocktailsResponse>
    @GET("lookup.php")
    suspend fun getCocktailDetail(@Query("i") id:String): Response<CocktailsResponse>
}