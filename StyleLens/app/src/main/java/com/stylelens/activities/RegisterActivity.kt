package com.stylelens.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stylelens.R
import com.stylelens.databinding.ActivityRegisterBinding
import com.stylelens.models.RegisterRequest
import com.stylelens.network.RetrofitClient
import com.stylelens.utils.SharedPrefsManager
import com.stylelens.utils.ValidationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var prefs: SharedPrefsManager
    private var selectedStylePreference = "Casual"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefs = SharedPrefsManager.getInstance(this)
        
        setupSpinner()
        setupListeners()
    }
    
    private fun setupSpinner() {
        // Style Preference Spinner
        val stylePreferences = arrayOf("Casual", "Formal", "Sporty", "Elegant", "Trendy")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stylePreferences)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStylePreference.adapter = adapter
        
        binding.spinnerStylePreference.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedStylePreference = stylePreferences[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    
    private fun setupListeners() {
        // Register Button
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val sustainabilityPref = binding.switchSustainability.isChecked
            
            val validation = ValidationUtils.validateRegisterInputs(name, email, password)
            if (validation.first) {
                performRegister(name, email, password, sustainabilityPref)
            } else {
                Toast.makeText(this, validation.second, Toast.LENGTH_SHORT).show()
            }
        }
        
        // Login TextView
        binding.tvLogin.setOnClickListener {
            finish()
        }
    }
    
    private fun performRegister(name: String, email: String, password: String, sustainabilityPref: Boolean) {
        // Show loading
        binding.btnRegister.isEnabled = false
        binding.btnRegister.text = "Registering..."
        
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.register(
                    RegisterRequest(name, email, password, sustainabilityPref)
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
                        
                        Toast.makeText(this@RegisterActivity, "Registration successful!", Toast.LENGTH_SHORT).show()
                        
                        // Navigate to Home
                        startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            response.body()?.message ?: "Registration failed",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.btnRegister.isEnabled = true
                        binding.btnRegister.text = "Register"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.btnRegister.isEnabled = true
                    binding.btnRegister.text = "Register"
                }
            }
        }
    }
}
