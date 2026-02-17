package com.stylelens.ai

/**
 * ColorMatcher - AI for Color Harmony and Palette Selection
 * Matches colors to skin tone and occasion using color theory
 */
class ColorMatcher {
    
    /**
     * Get color palette based on skin tone and occasion
     */
    fun getColorPalette(skinTone: String, occasion: String): String {
        return when (skinTone) {
            "Fair" -> when (occasion) {
                "Formal" -> "Neo Modern (Navy, White, Sky Blue)"
                "Casual" -> "Ocean Breeze (Aqua, Coral, Cream)"
                "Party" -> "Pastel Dream (Pink, Lavender, Mint)"
                else -> "Soft Neutrals (Beige, Cream, Light Gray)"
            }
            "Medium" -> when (occasion) {
                "Formal" -> "Earth Luxe (Brown, Tan, Gold)"
                "Casual" -> "Vibrant Street (Red, Orange, Purple)"
                "Party" -> "Jewel Tones (Emerald, Ruby, Sapphire)"
                else -> "Warm Neutrals (Camel, Olive, Rust)"
            }
            "Dark" -> when (occasion) {
                "Formal" -> "Classic Elegance (Black, White, Gold)"
                "Casual" -> "Bold Brights (Yellow, Fuchsia, Turquoise)"
                "Party" -> "Metallic Glam (Gold, Silver, Bronze)"
                else -> "Rich Tones (Burgundy, Forest, Navy)"
            }
            else -> "Calm Minimalist (White, Black, Gray)"
        }
    }
    
    /**
     * Get primary color recommendation
     */
    fun getPrimaryColor(skinTone: String): String {
        return when (skinTone) {
            "Fair" -> "#E8D5C4" // Soft Peach
            "Medium" -> "#D2B48C" // Tan
            "Dark" -> "#8B4513" // Saddle Brown
            else -> "#808080" // Gray
        }
    }
    
    /**
     * Get complementary colors
     */
    fun getComplementaryColors(baseColor: String): List<String> {
        // Simplified color harmony logic
        return when (baseColor.lowercase()) {
            "red" -> listOf("Green", "Teal")
            "blue" -> listOf("Orange", "Yellow")
            "green" -> listOf("Red", "Pink")
            "yellow" -> listOf("Purple", "Blue")
            "purple" -> listOf("Yellow", "Green")
            "orange" -> listOf("Blue", "Teal")
            else -> listOf("White", "Black", "Gray")
        }
    }
    
    /**
     * Check if colors clash
     */
    fun doColorsClash(color1: String, color2: String): Boolean {
        val clashingCombinations = listOf(
            Pair("Red", "Pink"),
            Pair("Orange", "Red"),
            Pair("Purple", "Pink"),
            Pair("Brown", "Black")
        )
        
        return clashingCombinations.any {
            (it.first.equals(color1, ignoreCase = true) && it.second.equals(color2, ignoreCase = true)) ||
            (it.second.equals(color1, ignoreCase = true) && it.first.equals(color2, ignoreCase = true))
        }
    }
    
    /**
     * Get mood-based color palette
     */
    fun getMoodPalette(mood: String): String {
        return when (mood.lowercase()) {
            "happy", "energetic" -> "Vibrant (Yellow, Orange, Red)"
            "calm", "peaceful" -> "Serene (Blue, Green, White)"
            "elegant", "sophisticated" -> "Classic (Black, White, Gold)"
            "romantic" -> "Soft (Pink, Rose, Cream)"
            "bold", "confident" -> "Power (Red, Black, White)"
            else -> "Balanced (Neutral tones)"
        }
    }
    
    /**
     * Calculate color harmony score (0-100)
     */
    fun calculateHarmonyScore(colors: List<String>): Int {
        if (colors.isEmpty()) return 0
        
        var score = 100
        
        // Check for clashing colors
        for (i in colors.indices) {
            for (j in i + 1 until colors.size) {
                if (doColorsClash(colors[i], colors[j])) {
                    score -= 20
                }
            }
        }
        
        // Bonus for complementary colors
        if (colors.size >= 2) {
            val complementary = getComplementaryColors(colors[0])
            if (colors.any { it in complementary }) {
                score += 10
            }
        }
        
        return score.coerceIn(0, 100)
    }
}
