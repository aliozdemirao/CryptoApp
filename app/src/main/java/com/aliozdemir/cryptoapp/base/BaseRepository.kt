package com.aliozdemir.cryptoapp.base

import com.aliozdemir.cryptoapp.R
import com.aliozdemir.cryptoapp.di.CryptoApp.Companion.getAppContext
import com.aliozdemir.cryptoapp.model.errorResponse.ErrorResponse
import com.aliozdemir.cryptoapp.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

abstract class BaseRepository {

    suspend fun <T> safeApiRequest(
        apiRequest: suspend () -> T
    ): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResult.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        NetworkResult.Error(
                            false, errorBodyParser(throwable.response()?.errorBody()?.string())
                        )
                    }

                    else -> NetworkResult.Error(true, throwable.localizedMessage)
                }
            }
        }
    }
}

private fun errorBodyParser(error: String?): String {
    error?.let {
        return try {
            val errorResponse = Gson().fromJson(error, ErrorResponse::class.java)
            val errorMessage = errorResponse.status?.errorMessage
            errorMessage ?: getAppContext().resources.getString(R.string.error_message)
        } catch (e: Exception) {
            getAppContext().resources.getString(R.string.error_message)
        }
    }
    return getAppContext().resources.getString(R.string.error_message)
}