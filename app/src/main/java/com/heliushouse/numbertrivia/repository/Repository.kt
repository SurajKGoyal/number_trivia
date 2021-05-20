package com.heliushouse.numbertrivia.repository

import com.heliushouse.numbertrivia.model.NumberResponse

interface Repository {
    suspend fun getTrivia(number: String, type: String): NumberResponse
}