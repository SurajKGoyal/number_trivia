package com.heliushouse.numbertrivia.model

import com.google.gson.annotations.SerializedName

data class NumberResponse(
    @SerializedName("text")
    val text: String?
)
