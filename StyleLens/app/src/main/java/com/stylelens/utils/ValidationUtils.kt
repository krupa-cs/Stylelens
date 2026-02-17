package com.stylelens.utils

import android.util.Patterns
import android.widget.EditText

object ValidationUtils {
    
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
    
    fun isValidName(name: String): Boolean {
        return name.isNotEmpty() && name.length >= 2
    }
    
    fun EditText.showError(message: String) {
        this.error = message
        this.requestFocus()
    }
    
    fun validateLoginInputs(email: String, password: String): Pair<Boolean, String> {
        return when {
            email.isEmpty() -> Pair(false, "Email is required")
            !isValidEmail(email) -> Pair(false, "Invalid email format")
            password.isEmpty() -> Pair(false, "Password is required")
            !isValidPassword(password) -> Pair(false, "Password must be at least 6 characters")
            else -> Pair(true, "")
        }
    }
    
    fun validateRegisterInputs(
        name: String,
        email: String,
        password: String
    ): Pair<Boolean, String> {
        return when {
            name.isEmpty() -> Pair(false, "Name is required")
            !isValidName(name) -> Pair(false, "Name must be at least 2 characters")
            email.isEmpty() -> Pair(false, "Email is required")
            !isValidEmail(email) -> Pair(false, "Invalid email format")
            password.isEmpty() -> Pair(false, "Password is required")
            !isValidPassword(password) -> Pair(false, "Password must be at least 6 characters")
            else -> Pair(true, "")
        }
    }
}
