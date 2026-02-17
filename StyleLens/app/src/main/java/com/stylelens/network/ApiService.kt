package com.stylelens.network

import com.stylelens.models.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    // Authentication
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>
    
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
    
    @GET("auth/me")
    suspend fun getCurrentUser(): Response<ApiResponse<User>>
    
    // Style Management
    @POST("style/profile")
    suspend fun createStyleProfile(@Body profile: StyleProfile): Response<ApiResponse<StyleProfile>>
    
    @POST("style/generate")
    suspend fun generateStyle(@Body request: StyleGenerateRequest): Response<ApiResponse<StyleResult>>
    
    @GET("style/history")
    suspend fun getStyleHistory(): Response<ApiResponse<List<StyleResult>>>
    
    @POST("style/favorites")
    suspend fun addToFavorites(@Body request: FavoriteRequest): Response<ApiResponse<FavoriteStyle>>
    
    // Products
    @GET("products")
    suspend fun getProducts(
        @Query("category") category: String? = null,
        @Query("minPrice") minPrice: Double? = null,
        @Query("maxPrice") maxPrice: Double? = null,
        @Query("minSustainability") minSustainability: Int? = null
    ): Response<ApiResponse<List<Product>>>
    
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ApiResponse<Product>>
}

// Request/Response models
data class ApiResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T? = null,
    val count: Int? = null
)

data class StyleGenerateRequest(
    val profileId: Int,
    val styleType: String,
    val occasion: String,
    val budget: Int
)

data class FavoriteRequest(
    val resultId: Int
)
