package com.stylelens.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.stylelens.R
import com.stylelens.utils.SharedPrefsManager

class SplashActivity : AppCompatActivity() {
    
    private val SPLASH_DELAY = 2000L // 2 seconds
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToNextScreen()
        }, SPLASH_DELAY)
    }
    
    private fun navigateToNextScreen() {
        val prefs = SharedPrefsManager.getInstance(this)
        val intent = if (prefs.isLoggedIn()) {
            Intent(this, HomeActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
