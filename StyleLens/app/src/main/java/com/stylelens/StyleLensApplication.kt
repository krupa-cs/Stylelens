package com.stylelens

import android.app.Application

class StyleLensApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    
    companion object {
        private lateinit var instance: StyleLensApplication
        
        fun getInstance(): StyleLensApplication {
            return instance
        }
    }
}
