package com.stylelens.ai

/**
 * SustainabilityCalculator - AI for Calculating Sustainability Scores
 * Predicts sustainability score based on user choices and behavior
 */
class SustainabilityCalculator {
    
    /**
     * Calculate overall sustainability score (0-100)
     */
    fun calculateScore(
        sustainabilityMode: Boolean,
        budget: Int,
        accessoriesIncluded: Boolean,
        reuseCount: Int = 0,
        ecoProductsCount: Int = 0
    ): Int {
        var score = 50 // Base score
        
        // Sustainability mode bonus
        if (sustainabilityMode) {
            score += 20
        }
        
        // Budget consideration (lower budget = less overconsumption)
        when {
            budget < 50 -> score += 15
            budget < 100 -> score += 10
            budget < 200 -> score += 5
            else -> score += 0
        }
        
        // Accessories (fewer accessories = less consumption)
        if (!accessoriesIncluded) {
            score += 5
        }
        
        // Reuse bonus
        score += (reuseCount * 3).coerceAtMost(15)
        
        // Eco products bonus
        score += (ecoProductsCount * 5).coerceAtMost(20)
        
        return score.coerceIn(0, 100)
    }
    
    /**
     * Get sustainability level description
     */
    fun getSustainabilityLevel(score: Int): String {
        return when {
            score >= 80 -> "Excellent - Eco Champion! 🌟"
            score >= 60 -> "Good - Keep it up! 🌱"
            score >= 40 -> "Average - Room for improvement 📈"
            else -> "Low - Consider sustainable choices 🌍"
        }
    }
    
    /**
     * Get sustainability tips based on score
     */
    fun getSustainabilityTips(score: Int): List<String> {
        val tips = mutableListOf<String>()
        
        if (score < 60) {
            tips.add("Try to reuse existing items in your wardrobe")
            tips.add("Choose sustainable fabrics like organic cotton or bamboo")
            tips.add("Buy quality over quantity - invest in timeless pieces")
        }
        
        if (score < 80) {
            tips.add("Look for eco-certified products")
            tips.add("Consider second-hand or vintage items")
            tips.add("Repair and upcycle old clothes instead of buying new")
        }
        
        tips.add("Support brands with transparent sustainability practices")
        tips.add("Minimize packaging waste by choosing eco-friendly packaging")
        
        return tips
    }
    
    /**
     * Calculate environmental impact score
     */
    fun calculateEnvironmentalImpact(
        materialType: String,
        productionMethod: String = "Standard"
    ): Int {
        var impact = 50 // Base impact (lower is better)
        
        // Material impact
        when (materialType.lowercase()) {
            "organic cotton", "bamboo", "hemp" -> impact -= 20
            "recycled polyester", "recycled materials" -> impact -= 15
            "cotton" -> impact -= 5
            "polyester", "nylon" -> impact += 10
            "leather" -> impact += 15
            else -> impact += 0
        }
        
        // Production method impact
        when (productionMethod.lowercase()) {
            "sustainable", "eco-friendly" -> impact -= 15
            "fair trade" -> impact -= 10
            "local" -> impact -= 5
            else -> impact += 0
        }
        
        return impact.coerceIn(0, 100)
    }
    
    /**
     * Predict future sustainability score based on current behavior
     */
    fun predictFutureScore(
        currentScore: Int,
        monthlyPurchases: Int,
        reuseRate: Double
    ): Int {
        var futureScore = currentScore
        
        // Penalize excessive purchases
        if (monthlyPurchases > 5) {
            futureScore -= (monthlyPurchases - 5) * 2
        }
        
        // Reward high reuse rate
        if (reuseRate > 0.7) {
            futureScore += 10
        } else if (reuseRate > 0.5) {
            futureScore += 5
        }
        
        return futureScore.coerceIn(0, 100)
    }
    
    /**
     * Calculate carbon footprint estimate (in kg CO2)
     */
    fun calculateCarbonFootprint(
        itemsCount: Int,
        averageItemWeight: Double = 0.5 // kg
    ): Double {
        // Simplified calculation: ~20 kg CO2 per kg of clothing
        return itemsCount * averageItemWeight * 20.0
    }
    
    /**
     * Get SDG 12 alignment score
     */
    fun getSDG12AlignmentScore(sustainabilityScore: Int): String {
        return when {
            sustainabilityScore >= 80 -> "Strongly Aligned with SDG 12 ✓✓✓"
            sustainabilityScore >= 60 -> "Moderately Aligned with SDG 12 ✓✓"
            sustainabilityScore >= 40 -> "Partially Aligned with SDG 12 ✓"
            else -> "Needs Improvement for SDG 12 Alignment"
        }
    }
}
