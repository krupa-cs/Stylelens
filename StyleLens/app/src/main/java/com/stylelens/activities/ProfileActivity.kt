package com.stylelens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stylelens.R
import com.stylelens.adapters.StyleAdapter
import com.stylelens.databinding.ActivityProfileBinding
import com.stylelens.models.StyleResult
import com.stylelens.utils.SharedPrefsManager

class ProfileActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityProfileBinding
    private lateinit var prefs: SharedPrefsManager
    private lateinit var styleAdapter: StyleAdapter
    private var styleHistory = mutableListOf<StyleResult>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefs = SharedPrefsManager.getInstance(this)
        
        setupToolbar()
        displayUserInfo()
        setupRecyclerView()
        loadStyleHistory()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Profile"
    }
    
    private fun displayUserInfo() {
        val userName = prefs.getUserName() ?: "User"
        val userEmail = prefs.getUserEmail() ?: "user@example.com"
        
        binding.tvUserName.text = userName
        binding.tvUserEmail.text = userEmail
        
        // Display sustainability stats
        val sustainabilityScore = 78 // In real app, calculate from user data
        binding.tvSustainabilityScore.text = "$sustainabilityScore/100"
        binding.progressSustainability.progress = sustainabilityScore
        
        // Display stats
        binding.tvTotalStyles.text = "15" // Total styles created
        binding.tvFavorites.text = "8" // Total favorites
        binding.tvReusedItems.text = "23" // Items reused
    }
    
    private fun setupRecyclerView() {
        styleAdapter = StyleAdapter(styleHistory) { style, position ->
            // Handle click
        }
        
        binding.recyclerViewHistory.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = styleAdapter
        }
    }
    
    private fun loadStyleHistory() {
        // Sample history (in real app, load from API)
        styleHistory = mutableListOf(
            StyleResult(1, 1, 1, "people", "Casual outfit", 85, "2024-02-01"),
            StyleResult(2, 1, 2, "space", "Living room design", 78, "2024-01-28"),
            StyleResult(3, 1, 3, "character", "Character design", 92, "2024-01-25")
        )
        
        styleAdapter.notifyDataSetChanged()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
