package com.ninestudios.meli.core.network

import android.content.Context
import com.ninestudios.meli.R
import com.ninestudios.meli.core.hasInternetConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

inline fun <reified T> toResult(context: Context, crossinline call: suspend () -> Response<T>): Flow<NetworkResult<T>> {
    return flow {
        val isInternetConnected = hasInternetConnection(context)
        if (isInternetConnected) {
            emit(NetworkResult.Loading(true))
            try {
                val c = call()
                if (c.isSuccessful && c.body() != null) {
                    emit(NetworkResult.Success(c.body()))
                } else {
                    emit(NetworkResult.Error(c.message()))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.toString()))
            }
        } else {
            emit(NetworkResult.Error(context.getString(R.string.error_no_internet)))
        }
    }.flowOn(Dispatchers.IO)
}