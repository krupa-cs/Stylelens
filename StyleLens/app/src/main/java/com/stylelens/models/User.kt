package com.stylelens.models

data class User(
    val userId: Int = 0,
    val name: String = "",
    val email: String = "",
    val sustainabilityPreference: Boolean = true,
    val createdAt: String = ""
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val sustainability_preference: Boolean = true
)

data class AuthResponse(
    val success: Boolean,
    val message: String,
    val data: AuthData?
)

data class AuthData(
    val userId: Int,
    val name: String,
    val email: String,
    val token: String,
    val sustainability_preference: Boolean = true
)
