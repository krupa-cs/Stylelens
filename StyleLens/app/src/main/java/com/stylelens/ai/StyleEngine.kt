package com.stylelens.ai

/**
 * StyleEngine - Rule-Based AI for Personal Styling
 * Provides outfit recommendations based on body type and occasion
 */
class StyleEngine {
    
    /**
     * Get top wear recommendation based on body type and occasion
     */
    fun getTopWear(bodyType: String, occasion: String): String {
        return when (bodyType) {
            "Pear" -> when (occasion) {
                "Formal" -> "Structured blazer with shoulder pads"
                "Casual" -> "Fitted top with boat neck"
                "Party" -> "Embellished top with statement sleeves"
                else -> "V-neck blouse"
            }
            "Apple" -> when (occasion) {
                "Formal" -> "V-neck blazer"
                "Casual" -> "Empire waist top"
                "Party" -> "Wrap top with belt"
                else -> "Flowy tunic"
            }
            "Hourglass" -> when (occasion) {
                "Formal" -> "Fitted blazer"
                "Casual" -> "Wrap top"
                "Party" -> "Bodycon dress"
                else -> "Belted blouse"
            }
            "Rectangle" -> when (occasion) {
                "Formal" -> "Peplum blazer"
                "Casual" -> "Ruffled top"
                "Party" -> "Crop top with high-waist bottom"
                else -> "Layered top"
            }
            "Inverted Triangle" -> when (occasion) {
                "Formal" -> "Single-breasted blazer"
                "Casual" -> "Scoop neck top"
                "Party" -> "Off-shoulder top"
                else -> "Simple blouse"
            }
            else -> "Classic shirt"
        }
    }
    
    /**
     * Get bottom wear recommendation based on body type and occasion
     */
    fun getBottomWear(bodyType: String, occasion: String): String {
        return when (bodyType) {
            "Pear" -> when (occasion) {
                "Formal" -> "Dark colored straight pants"
                "Casual" -> "Bootcut jeans"
                "Party" -> "A-line skirt"
                else -> "Wide leg pants"
            }
            "Apple" -> when (occasion) {
                "Formal" -> "Straight leg trousers"
                "Casual" -> "Mid-rise jeans"
                "Party" -> "Pencil skirt"
                else -> "Palazzo pants"
            }
            "Hourglass" -> when (occasion) {
                "Formal" -> "High-waisted pants"
                "Casual" -> "Skinny jeans"
                "Party" -> "Bodycon skirt"
                else -> "Fitted trousers"
            }
            "Rectangle" -> when (occasion) {
                "Formal" -> "Wide leg pants"
                "Casual" -> "Boyfriend jeans"
                "Party" -> "Pleated skirt"
                else -> "Cargo pants"
            }
            "Inverted Triangle" -> when (occasion) {
                "Formal" -> "Flared pants"
                "Casual" -> "Bootcut jeans"
                "Party" -> "Full skirt"
                else -> "Wide leg trousers"
            }
            else -> "Classic jeans"
        }
    }
    
    /**
     * Get accessories recommendation based on occasion
     */
    fun getAccessories(occasion: String): List<String> {
        return when (occasion) {
            "Formal" -> listOf("Watch", "Minimal jewelry", "Leather bag", "Classic belt")
            "Casual" -> listOf("Sunglasses", "Canvas bag", "Bracelet", "Casual watch")
            "Party" -> listOf("Statement earrings", "Clutch", "Bold necklace", "Cocktail ring")
            "Business" -> listOf("Professional watch", "Laptop bag", "Simple earrings", "Belt")
            "Wedding" -> listOf("Elegant jewelry", "Designer clutch", "Hair accessories", "Shawl")
            else -> listOf("Simple accessories", "Bag", "Watch")
        }
    }
    
    /**
     * Get style tips based on body type and occasion
     */
    fun getStyleTips(bodyType: String, occasion: String): List<String> {
        val tips = mutableListOf<String>()
        
        // Body type specific tips
        when (bodyType) {
            "Pear" -> {
                tips.add("Draw attention to upper body with bright colors")
                tips.add("Avoid tight bottoms, opt for darker colors below")
            }
            "Apple" -> {
                tips.add("Create definition at waist with belts")
                tips.add("V-necks elongate the torso")
            }
            "Hourglass" -> {
                tips.add("Highlight your waist with fitted styles")
                tips.add("Avoid boxy silhouettes")
            }
            "Rectangle" -> {
                tips.add("Create curves with layering and ruffles")
                tips.add("Belts help define waist")
            }
            "Inverted Triangle" -> {
                tips.add("Balance proportions with volume on bottom")
                tips.add("Avoid shoulder pads")
            }
        }
        
        // Occasion specific tips
        when (occasion) {
            "Formal" -> tips.add("Stick to neutral colors and classic cuts")
            "Casual" -> tips.add("Mix and match for personal expression")
            "Party" -> tips.add("Don't be afraid to add sparkle and bold colors")
        }
        
        // Sustainability tip
        tips.add("Consider sustainable fabrics and reuse existing items")
        
        return tips
    }
}
