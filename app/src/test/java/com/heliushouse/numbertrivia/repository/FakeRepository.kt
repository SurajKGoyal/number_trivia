package com.heliushouse.numbertrivia.repository

import com.heliushouse.numbertrivia.model.NumberResponse

class FakeRepository : Repository {

    override suspend fun getTrivia(number: String, type: String): NumberResponse {
        return NumberResponse("Test")
    }
}