package com.aliozdemir.cryptoapp.model.errorResponse


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: Status?
)