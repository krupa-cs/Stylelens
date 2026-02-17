package com.stylelens.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.stylelens.R
import com.stylelens.databinding.ActivityHomeBinding
import com.stylelens.fragments.*
import com.stylelens.utils.SharedPrefsManager

class HomeActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHomeBinding
    private lateinit var prefs: SharedPrefsManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefs = SharedPrefsManager.getInstance(this)
        
        setupToolbar()
        setupBottomNavigation()
        
        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(DashboardFragment())
        }
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Style Lens"
        supportActionBar?.subtitle = "AI-Powered Sustainable Styling"
    }
    
    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    loadFragment(DashboardFragment())
                    supportActionBar?.title = "Dashboard"
                    true
                }
                R.id.nav_people -> {
                    loadFragment(PeopleStyleFragment())
                    supportActionBar?.title = "People Styling"
                    true
                }
                R.id.nav_space -> {
                    loadFragment(SpaceStyleFragment())
                    supportActionBar?.title = "Space Styling"
                    true
                }
                R.id.nav_character -> {
                    loadFragment(CharacterStyleFragment())
                    supportActionBar?.title = "Character Styling"
                    true
                }
                R.id.nav_favorites -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    false
                }
                else -> false
            }
        })
    }
    
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Options Menu (Toolbar Menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.action_logout -> {
                performLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun performLogout() {
        prefs.clearAll()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
