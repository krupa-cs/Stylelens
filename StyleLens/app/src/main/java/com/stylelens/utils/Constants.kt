package com.stylelens.utils

object Constants {
    // API Configuration
    const val BASE_URL = "http://10.0.2.2:3000/api/v1/" // For emulator
    // For physical device, use: "http://YOUR_IP:3000/api/v1/"
    
    // SharedPreferences Keys
    const val PREF_NAME = "StyleLensPrefs"
    const val KEY_TOKEN = "auth_token"
    const val KEY_USER_ID = "user_id"
    const val KEY_USER_NAME = "user_name"
    const val KEY_USER_EMAIL = "user_email"
    const val KEY_IS_LOGGED_IN = "is_logged_in"
    
    // Request Codes
    const val REQUEST_IMAGE_PICK = 100
    const val REQUEST_CAMERA = 101
    
    // Body Types
    val BODY_TYPES = arrayOf("Pear", "Apple", "Hourglass", "Rectangle", "Inverted Triangle")
    
    // Skin Tones
    val SKIN_TONES = arrayOf("Fair", "Medium", "Dark")
    
    // Occasions
    val OCCASIONS = arrayOf("Formal", "Casual", "Party", "Business", "Wedding")
    
    // Face Shapes
    val FACE_SHAPES = arrayOf("Oval", "Round", "Square", "Heart", "Diamond")
    
    // Style Types
    const val STYLE_TYPE_PEOPLE = "people"
    const val STYLE_TYPE_SPACE = "space"
    const val STYLE_TYPE_CHARACTER = "character"
    const val STYLE_TYPE_PRODUCT = "product"
    const val STYLE_TYPE_SOCIAL_MEDIA = "social_media"
    
    // Sustainability Thresholds
    const val SUSTAINABILITY_EXCELLENT = 80
    const val SUSTAINABILITY_GOOD = 60
    const val SUSTAINABILITY_AVERAGE = 40
}
