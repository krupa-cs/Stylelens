package com.stylelens.models

// Separate file for each model as per structure

data class StyleResult(
    val resultId: Int = 0,
    val profileId: Int = 0,
    val paletteId: Int? = null,
    val styleType: String = "",
    val recommendationText: String = "",
    val sustainabilityScore: Int = 0,
    val createdAt: String = ""
)
