package com.stylelens.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stylelens.R
import com.stylelens.ai.ColorMatcher
import com.stylelens.ai.RecommendationEngine
import com.stylelens.ai.StyleEngine
import com.stylelens.ai.SustainabilityCalculator
import com.stylelens.databinding.ActivityStyleResultBinding

class StyleResultActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityStyleResultBinding
    private var sustainabilityScore = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStyleResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        displayResults()
        setupListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Style Results"
    }
    
    private fun displayResults() {
        // Get data from intent
        val bodyType = intent.getStringExtra("bodyType") ?: ""
        val skinTone = intent.getStringExtra("skinTone") ?: ""
        val occasion = intent.getStringExtra("occasion") ?: ""
        val gender = intent.getStringExtra("gender") ?: "Female"
        val budget = intent.getIntExtra("budget", 100)
        val accessories = intent.getBooleanExtra("accessories", false)
        val sustainability = intent.getBooleanExtra("sustainability", false)
        val colorPreference = intent.getStringExtra("colorPreference") ?: ""
        
        // Use AI Engines to generate recommendations
        val styleEngine = StyleEngine()
        val colorMatcher = ColorMatcher()
        val sustainabilityCalculator = SustainabilityCalculator()
        val recommendationEngine = RecommendationEngine()
        
        // Generate style recommendation
        val topWear = styleEngine.getTopWear(bodyType, occasion)
        val bottomWear = styleEngine.getBottomWear(bodyType, occasion)
        val accessoriesList = styleEngine.getAccessories(occasion)
        
        // Get color palette
        val colorPalette = colorMatcher.getColorPalette(skinTone, occasion)
        
        // Calculate sustainability score
        sustainabilityScore = sustainabilityCalculator.calculateScore(
            sustainability,
            budget,
            accessories
        )
        
        // Get recommendations
        val tips = recommendationEngine.getStyleTips(bodyType, occasion)
        
        // Display results
        binding.tvBodyType.text = "Body Type: $bodyType"
        binding.tvSkinTone.text = "Skin Tone: $skinTone"
        binding.tvOccasion.text = "Occasion: $occasion"
        
        binding.tvTopWear.text = "Top: $topWear"
        binding.tvBottomWear.text = "Bottom: $bottomWear"
        binding.tvAccessories.text = "Accessories: ${accessoriesList.joinToString(", ")}"
        
        binding.tvColorPalette.text = "Color Palette: $colorPalette"
        binding.tvBudget.text = "Budget: $$budget"
        
        binding.tvSustainabilityScore.text = "Sustainability Score: $sustainabilityScore/100"
        binding.progressSustainability.progress = sustainabilityScore
        
        binding.tvRecommendations.text = tips.joinToString("\n• ", "• ")
        
        // Set rating bar
        binding.ratingBar.rating = 0f
    }
    
    private fun setupListeners() {
        // Save to Favorites Button
        binding.btnSaveFavorite.setOnClickListener {
            Toast.makeText(this, "Saved to favorites!", Toast.LENGTH_SHORT).show()
        }
        
        // Share Button
        binding.btnShare.setOnClickListener {
            shareResults()
        }
        
        // View Products Button
        binding.btnViewProducts.setOnClickListener {
            startActivity(Intent(this, ProductCatalogActivity::class.java))
        }
        
        // Rating Bar
        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            Toast.makeText(this, "Rated: $rating stars", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun shareResults() {
        val shareText = """
            Check out my Style Lens recommendation!
            
            Occasion: ${intent.getStringExtra("occasion")}
            Sustainability Score: $sustainabilityScore/100
            
            Download Style Lens - AI-Powered Sustainable Styling
        """.trimIndent()
        
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
