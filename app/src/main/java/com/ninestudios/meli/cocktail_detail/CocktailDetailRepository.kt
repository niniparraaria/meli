package com.ninestudios.meli.cocktail_detail

import android.content.Context
import com.ninestudios.meli.cocktails.CocktailsResponse
import com.ninestudios.meli.core.network.NetworkResult
import com.ninestudios.meli.core.network.RemoteDataSource
import com.ninestudios.meli.core.network.toResult
import kotlinx.coroutines.flow.Flow

class CocktailDetailRepository(private val remoteDataSource: RemoteDataSource, private val context: Context) {

    suspend fun getCocktailsById(id:String): Flow<NetworkResult<CocktailsResponse>> {
        return toResult(context){
            remoteDataSource.getCocktailsByID(id)
        }
    }

}