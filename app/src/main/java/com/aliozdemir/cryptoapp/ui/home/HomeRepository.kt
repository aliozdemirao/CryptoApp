package com.aliozdemir.cryptoapp.ui.home

import com.aliozdemir.cryptoapp.base.BaseRepository
import com.aliozdemir.cryptoapp.network.ApiFactory
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiFactory: ApiFactory) : BaseRepository() {

    suspend fun getData(apiKey: String, limit: String) = safeApiRequest {
        apiFactory.getData(apiKey, limit)
    }
}