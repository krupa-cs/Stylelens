package com.stylelens.models

data class StyleProfile(
    val profileId: Int = 0,
    val userId: Int = 0,
    val bodyType: String = "",
    val faceShape: String = "",
    val skinTone: String = "",
    val hairType: String = "",
    val height: Double = 0.0,
    val preferences: String = "",
    val createdAt: String = ""
)

data class StyleResult(
    val resultId: Int = 0,
    val profileId: Int = 0,
    val paletteId: Int? = null,
    val styleType: String = "",
    val recommendationText: String = "",
    val sustainabilityScore: Int = 0,
    val createdAt: String = ""
)

data class ColorPalette(
    val paletteId: Int = 0,
    val paletteName: String = "",
    val primaryColor: String = "",
    val secondaryColor: String = "",
    val accentColor: String = "",
    val usageType: String = "",
    val createdAt: String = ""
)

data class Product(
    val productId: Int = 0,
    val category: String = "",
    val name: String = "",
    val color: String = "",
    val price: Double = 0.0,
    val sustainabilityRating: Int = 0,
    val imageUrl: String = "",
    val createdAt: String = ""
)

data class FavoriteStyle(
    val favoriteId: Int = 0,
    val userId: Int = 0,
    val resultId: Int = 0,
    val savedDate: String = ""
)

data class QuickAction(
    val id: Int,
    val title: String,
    val icon: Int,
    val type: String
)
