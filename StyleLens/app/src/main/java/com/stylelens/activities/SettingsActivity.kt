package com.stylelens.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stylelens.R
import com.stylelens.databinding.ActivitySettingsBinding
import com.stylelens.utils.SharedPrefsManager

class SettingsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var prefs: SharedPrefsManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefs = SharedPrefsManager.getInstance(this)
        
        setupToolbar()
        setupListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Settings"
    }
    
    private fun setupListeners() {
        // Dark Mode Switch
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Dark mode enabled", Toast.LENGTH_SHORT).show()
                // In real app, apply dark theme
            } else {
                Toast.makeText(this, "Dark mode disabled", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Notifications Switch
        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Notifications enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifications disabled", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Sustainability Mode Switch
        binding.switchSustainability.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Sustainability mode ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sustainability mode OFF", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Logout Button
        binding.btnLogout.setOnClickListener {
            performLogout()
        }
        
        // Clear Cache Button
        binding.btnClearCache.setOnClickListener {
            Toast.makeText(this, "Cache cleared", Toast.LENGTH_SHORT).show()
        }
        
        // About Button
        binding.btnAbout.setOnClickListener {
            Toast.makeText(this, "Style Lens v1.0 - AI-Powered Sustainable Styling", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun performLogout() {
        prefs.clearAll()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
