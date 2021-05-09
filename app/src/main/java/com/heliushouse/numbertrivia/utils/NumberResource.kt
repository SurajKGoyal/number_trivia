package com.heliushouse.numbertrivia.utils

sealed class NumberResource {
    data class Success(val trivia: String): NumberResource()
    data class Error(val error: String): NumberResource()
    data class Loading(val state: String): NumberResource()
}
