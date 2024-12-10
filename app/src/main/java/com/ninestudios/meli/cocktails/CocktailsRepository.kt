package com.ninestudios.meli.cocktails

import android.content.Context
import com.ninestudios.meli.core.network.NetworkResult
import com.ninestudios.meli.core.network.RemoteDataSource
import com.ninestudios.meli.core.network.toResult
import kotlinx.coroutines.flow.Flow

class CocktailsRepository(private val remoteDataSource: RemoteDataSource, private val context: Context) {

    suspend fun getCocktailsByName(name:String): Flow<NetworkResult<CocktailsResponse>> {
        return toResult(context){
            remoteDataSource.getCocktailsByName(name)
        }
    }

}