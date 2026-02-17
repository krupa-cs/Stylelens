package com.stylelens.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        Constants.PREF_NAME,
        Context.MODE_PRIVATE
    )
    
    fun saveAuthToken(token: String) {
        prefs.edit().putString(Constants.KEY_TOKEN, token).apply()
    }
    
    fun getAuthToken(): String? {
        return prefs.getString(Constants.KEY_TOKEN, null)
    }
    
    fun saveUserId(userId: Int) {
        prefs.edit().putInt(Constants.KEY_USER_ID, userId).apply()
    }
    
    fun getUserId(): Int {
        return prefs.getInt(Constants.KEY_USER_ID, -1)
    }
    
    fun saveUserName(name: String) {
        prefs.edit().putString(Constants.KEY_USER_NAME, name).apply()
    }
    
    fun getUserName(): String? {
        return prefs.getString(Constants.KEY_USER_NAME, null)
    }
    
    fun saveUserEmail(email: String) {
        prefs.edit().putString(Constants.KEY_USER_EMAIL, email).apply()
    }
    
    fun getUserEmail(): String? {
        return prefs.getString(Constants.KEY_USER_EMAIL, null)
    }
    
    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(Constants.KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }
    
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(Constants.KEY_IS_LOGGED_IN, false)
    }
    
    fun clearAll() {
        prefs.edit().clear().apply()
    }
    
    companion object {
        @Volatile
        private var instance: SharedPrefsManager? = null
        
        fun getInstance(context: Context): SharedPrefsManager {
            return instance ?: synchronized(this) {
                instance ?: SharedPrefsManager(context.applicationContext).also {
                    instance = it
                }
            }
        }
    }
}
