package com.heliushouse.numbertrivia.api

import com.heliushouse.numbertrivia.model.NumberResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberService {

    @GET("{number}/{type}?json")
    suspend fun getInfo(@Path("number") number: String, @Path("type") type: String): NumberResponse

    companion object {
        const val NUMBER_API_URL = "http://numbersapi.com/"
    }
}