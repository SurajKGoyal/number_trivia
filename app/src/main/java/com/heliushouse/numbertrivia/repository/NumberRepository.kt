package com.heliushouse.numbertrivia.repository

import com.heliushouse.numbertrivia.api.NumberService
import com.heliushouse.numbertrivia.model.NumberResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumberRepository @Inject constructor(private val apiService: NumberService) : Repository {
    override suspend fun getTrivia(number: String, type: String): NumberResponse {
        return apiService.getInfo(number, type)
    }
}