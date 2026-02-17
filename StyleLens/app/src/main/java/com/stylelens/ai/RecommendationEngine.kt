package com.stylelens.ai

import kotlin.math.sqrt

/**
 * RecommendationEngine - Collaborative Filtering AI
 * Provides personalized recommendations based on similar users
 */
class RecommendationEngine {
    
    // Sample user-item matrix for collaborative filtering
    private val userPreferences = mapOf(
        1 to mapOf("Casual" to 5, "Formal" to 3, "Party" to 4),
        2 to mapOf("Casual" to 4, "Formal" to 5, "Party" to 2),
        3 to mapOf("Casual" to 5, "Formal" to 2, "Party" to 5),
        4 to mapOf("Casual" to 3, "Formal" to 4, "Party" to 3)
    )
    
    /**
     * Calculate similarity between two users using cosine similarity
     */
    fun calculateUserSimilarity(user1Prefs: Map<String, Int>, user2Prefs: Map<String, Int>): Double {
        val commonItems = user1Prefs.keys.intersect(user2Prefs.keys)
        
        if (commonItems.isEmpty()) return 0.0
        
        var dotProduct = 0.0
        var magnitude1 = 0.0
        var magnitude2 = 0.0
        
        for (item in commonItems) {
            val rating1 = user1Prefs[item] ?: 0
            val rating2 = user2Prefs[item] ?: 0
            
            dotProduct += rating1 * rating2
            magnitude1 += rating1 * rating1
            magnitude2 += rating2 * rating2
        }
        
        if (magnitude1 == 0.0 || magnitude2 == 0.0) return 0.0
        
        return dotProduct / (sqrt(magnitude1) * sqrt(magnitude2))
    }
    
    /**
     * Find similar users
     */
    fun findSimilarUsers(userId: Int, topN: Int = 3): List<Pair<Int, Double>> {
        val currentUserPrefs = userPreferences[userId] ?: return emptyList()
        
        val similarities = mutableListOf<Pair<Int, Double>>()
        
        for ((otherUserId, otherPrefs) in userPreferences) {
            if (otherUserId != userId) {
                val similarity = calculateUserSimilarity(currentUserPrefs, otherPrefs)
                similarities.add(Pair(otherUserId, similarity))
            }
        }
        
        return similarities.sortedByDescending { it.second }.take(topN)
    }
    
    /**
     * Get recommendations based on similar users
     */
    fun getRecommendations(userId: Int): List<String> {
        val recommendations = mutableListOf<String>()
        val similarUsers = findSimilarUsers(userId)
        
        if (similarUsers.isEmpty()) {
            return getDefaultRecommendations()
        }
        
        // Get items liked by similar users
        for ((similarUserId, _) in similarUsers) {
            val similarUserPrefs = userPreferences[similarUserId] ?: continue
            
            for ((item, rating) in similarUserPrefs) {
                if (rating >= 4) {
                    recommendations.add(item)
                }
            }
        }
        
        return recommendations.distinct()
    }
    
    /**
     * Get style tips based on user behavior
     */
    fun getStyleTips(bodyType: String, occasion: String): List<String> {
        val tips = mutableListOf<String>()
        
        // Collaborative filtering insights
        tips.add("Users with similar preferences also liked: Minimalist accessories")
        tips.add("Trending among similar users: Sustainable fashion choices")
        
        // Personalized tips
        when (occasion) {
            "Formal" -> {
                tips.add("90% of similar users prefer neutral colors for formal events")
                tips.add("Classic cuts are most popular in your style group")
            }
            "Casual" -> {
                tips.add("Users like you often mix vintage with modern pieces")
                tips.add("Comfort + style is the top priority for your group")
            }
            "Party" -> {
                tips.add("Bold colors are trending in your style community")
                tips.add("Statement accessories are popular among similar users")
            }
        }
        
        return tips
    }
    
    /**
     * Get default recommendations
     */
    private fun getDefaultRecommendations(): List<String> {
        return listOf(
            "Casual everyday wear",
            "Sustainable basics",
            "Versatile accessories",
            "Timeless pieces"
        )
    }
    
    /**
     * Predict user rating for an item
     */
    fun predictRating(userId: Int, item: String): Double {
        val similarUsers = findSimilarUsers(userId)
        
        if (similarUsers.isEmpty()) return 3.0 // Default rating
        
        var weightedSum = 0.0
        var similaritySum = 0.0
        
        for ((similarUserId, similarity) in similarUsers) {
            val rating = userPreferences[similarUserId]?.get(item)
            if (rating != null) {
                weightedSum += similarity * rating
                similaritySum += similarity
            }
        }
        
        return if (similaritySum > 0) weightedSum / similaritySum else 3.0
    }
    
    /**
     * Get trending items
     */
    fun getTrendingItems(): List<String> {
        val itemCounts = mutableMapOf<String, Int>()
        
        for (userPrefs in userPreferences.values) {
            for ((item, rating) in userPrefs) {
                if (rating >= 4) {
                    itemCounts[item] = (itemCounts[item] ?: 0) + 1
                }
            }
        }
        
        return itemCounts.entries
            .sortedByDescending { it.value }
            .take(5)
            .map { it.key }
    }
    
    /**
     * Get personalized outfit suggestions
     */
    fun getOutfitSuggestions(userId: Int, occasion: String): List<String> {
        val suggestions = mutableListOf<String>()
        val recommendations = getRecommendations(userId)
        
        if (occasion in recommendations) {
            suggestions.add("Complete $occasion outfit based on your preferences")
            suggestions.add("Mix and match from your existing wardrobe")
            suggestions.add("Add one statement piece to elevate the look")
        } else {
            suggestions.add("Try a new $occasion style based on trending choices")
            suggestions.add("Explore sustainable options for this occasion")
        }
        
        return suggestions
    }
}
