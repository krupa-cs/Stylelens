package com.stylelens.models

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
