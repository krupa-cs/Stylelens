package com.stylelens.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stylelens.R
import com.stylelens.databinding.ActivityLoginBinding
import com.stylelens.models.User
import com.stylelens.network.RetrofitClient
import com.stylelens.utils.SharedPrefsManager
import com.stylelens.utils.ValidationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefs: SharedPrefsManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefs = SharedPrefsManager.getInstance(this)
        
        setupListeners()
    }
    
    private fun setupListeners() {
        // Login Button
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            
            val validation = ValidationUtils.validateLoginInputs(email, password)
            if (validation.first) {
                performLogin(email, password)
            } else {
                Toast.makeText(this, validation.second, Toast.LENGTH_SHORT).show()
            }
        }
        
        // Register TextView
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    
    private fun performLogin(email: String, password: String) {
        // Show loading
        binding.btnLogin.isEnabled = false
        binding.btnLogin.text = "Logging in..."
        
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.login(
                    com.stylelens.models.LoginRequest(email, password)
                )
                
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        val authData = response.body()?.data
                        
                        // Save user data
                        authData?.let {
                            prefs.saveAuthToken(it.token)
                            prefs.saveUserId(it.userId)
                            prefs.saveUserName(it.name)
                            prefs.saveUserEmail(it.email)
                            prefs.setLoggedIn(true)
                        }
                        
                        Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                        
                        // Navigate to Home
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            response.body()?.message ?: "Login failed",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.btnLogin.isEnabled = true
                        binding.btnLogin.text = "Login"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.text = "Login"
                }
            }
        }
    }
}
