package com.heliushouse.numbertrivia.repository

import com.heliushouse.numbertrivia.api.NumberService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumberRepository @Inject constructor(val apiService: NumberService) {
    suspend fun getTrivia(number: String, type: String) = apiService.getInfo(number, type)
}