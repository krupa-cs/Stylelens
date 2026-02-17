# STYLE LENS - Android Project Structure

## 📁 Complete Android Project File Structure

```
StyleLens/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/stylelens/
│   │   │   │   ├── activities/
│   │   │   │   │   ├── SplashActivity.kt
│   │   │   │   │   ├── LoginActivity.kt
│   │   │   │   │   ├── RegisterActivity.kt
│   │   │   │   │   ├── HomeActivity.kt
│   │   │   │   │   ├── StyleInputActivity.kt
│   │   │   │   │   ├── StyleResultActivity.kt
│   │   │   │   │   ├── ProductCatalogActivity.kt
│   │   │   │   │   ├── FavoritesActivity.kt
│   │   │   │   │   ├── ProfileActivity.kt
│   │   │   │   │   └── SettingsActivity.kt
│   │   │   │   │
│   │   │   │   ├── fragments/
│   │   │   │   │   ├── DashboardFragment.kt
│   │   │   │   │   ├── PeopleStyleFragment.kt
│   │   │   │   │   ├── SpaceStyleFragment.kt
│   │   │   │   │   ├── CharacterStyleFragment.kt
│   │   │   │   │   ├── SocialMediaFragment.kt
│   │   │   │   │   └── ProductFragment.kt
│   │   │   │   │
│   │   │   │   ├── adapters/
│   │   │   │   │   ├── QuickActionAdapter.kt
│   │   │   │   │   ├── PaletteAdapter.kt
│   │   │   │   │   ├── StyleHistoryAdapter.kt
│   │   │   │   │   ├── ProductAdapter.kt
│   │   │   │   │   └── FavoritesAdapter.kt
│   │   │   │   │
│   │   │   │   ├── models/
│   │   │   │   │   ├── User.kt
│   │   │   │   │   ├── StyleProfile.kt
│   │   │   │   │   ├── StyleResult.kt
│   │   │   │   │   ├── ColorPalette.kt
│   │   │   │   │   ├── Product.kt
│   │   │   │   │   └── FavoriteStyle.kt
│   │   │   │   │
│   │   │   │   ├── viewmodels/
│   │   │   │   │   ├── AuthViewModel.kt
│   │   │   │   │   ├── StyleViewModel.kt
│   │   │   │   │   ├── ProductViewModel.kt
│   │   │   │   │   └── ProfileViewModel.kt
│   │   │   │   │
│   │   │   │   ├── repository/
│   │   │   │   │   ├── AuthRepository.kt
│   │   │   │   │   ├── StyleRepository.kt
│   │   │   │   │   ├── ProductRepository.kt
│   │   │   │   │   └── ProfileRepository.kt
│   │   │   │   │
│   │   │   │   ├── network/
│   │   │   │   │   ├── ApiService.kt
│   │   │   │   │   ├── RetrofitClient.kt
│   │   │   │   │   └── ApiResponse.kt
│   │   │   │   │
│   │   │   │   ├── ai/
│   │   │   │   │   ├── PersonalStylingAI.kt
│   │   │   │   │   ├── ColorHarmonyML.kt
│   │   │   │   │   ├── SustainabilityPredictor.kt
│   │   │   │   │   ├── RecommendationEngine.kt
│   │   │   │   │   └── MoodBasedStyling.kt
│   │   │   │   │
│   │   │   │   ├── utils/
│   │   │   │   │   ├── Constants.kt
│   │   │   │   │   ├── SharedPrefsManager.kt
│   │   │   │   │   ├── ImageLoader.kt
│   │   │   │   │   └── ValidationUtils.kt
│   │   │   │   │
│   │   │   │   └── StyleLensApplication.kt
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_splash.xml
│   │   │   │   │   ├── activity_login.xml
│   │   │   │   │   ├── activity_register.xml
│   │   │   │   │   ├── activity_home.xml
│   │   │   │   │   ├── activity_style_input.xml
│   │   │   │   │   ├── activity_style_result.xml
│   │   │   │   │   ├── activity_product_catalog.xml
│   │   │   │   │   ├── activity_favorites.xml
│   │   │   │   │   ├── activity_profile.xml
│   │   │   │   │   ├── activity_settings.xml
│   │   │   │   │   ├── fragment_dashboard.xml
│   │   │   │   │   ├── fragment_people_style.xml
│   │   │   │   │   ├── fragment_space_style.xml
│   │   │   │   │   ├── fragment_character_style.xml
│   │   │   │   │   ├── fragment_social_media.xml
│   │   │   │   │   ├── fragment_product.xml
│   │   │   │   │   ├── item_quick_action.xml
│   │   │   │   │   ├── item_palette.xml
│   │   │   │   │   ├── item_style_history.xml
│   │   │   │   │   ├── item_product.xml
│   │   │   │   │   └── item_favorite.xml
│   │   │   │   │
│   │   │   │   ├── menu/
│   │   │   │   │   ├── menu_main.xml
│   │   │   │   │   ├── menu_context.xml
│   │   │   │   │   ├── menu_popup.xml
│   │   │   │   │   └── menu_bottom_nav.xml
│   │   │   │   │
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── themes.xml
│   │   │   │   │   └── dimens.xml
│   │   │   │   │
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── ic_launcher.xml
│   │   │   │   │   ├── ic_person.xml
│   │   │   │   │   ├── ic_home.xml
│   │   │   │   │   ├── ic_favorite.xml
│   │   │   │   │   └── bg_button.xml
│   │   │   │   │
│   │   │   │   └── navigation/
│   │   │   │       └── nav_graph.xml
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   └── test/
│   │       └── java/com/stylelens/
│   │           ├── AIModuleTest.kt
│   │           └── DatabaseTest.kt
│   │
│   └── build.gradle.kts
│
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## 📦 Dependencies (build.gradle.kts - App Level)

```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.stylelens"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.stylelens"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlinOptions {
        jvmTarget = "17"
    }
    
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    
    // Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    
    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    
    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    
    // Retrofit (Network)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    
    // Image Loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")
    
    // Room Database (Local caching)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // Gson
    implementation("com.google.code.gson:gson:2.10.1")
    
    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
```

## 🔧 Configuration Files

### AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    <application
        android:name=".StyleLensApplication"
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.StyleLens"
        android:usesCleartextTraffic="true"
        tools:targetApi="31">

        <!-- Splash Activity -->
        <activity
            android:name=".activities.SplashActivity"
            android:exported="true"
            android:theme="@style/Theme.StyleLens.Splash">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- Login Activity -->
        <activity
            android:name=".activities.LoginActivity"
            android:exported="false"
            android:windowSoftInputMode="adjustResize" />

        <!-- Register Activity -->
        <activity
            android:name=".activities.RegisterActivity"
            android:exported="false"
            android:windowSoftInputMode="adjustResize" />

        <!-- Home Activity -->
        <activity
            android:name=".activities.HomeActivity"
            android:exported="false"
            android:launchMode="singleTop" />

        <!-- Style Input Activity -->
        <activity
            android:name=".activities.StyleInputActivity"
            android:exported="false"
            android:windowSoftInputMode="adjustResize" />

        <!-- Style Result Activity -->
        <activity
            android:name=".activities.StyleResultActivity"
            android:exported="false" />

        <!-- Product Catalog Activity -->
        <activity
            android:name=".activities.ProductCatalogActivity"
            android:exported="false" />

        <!-- Favorites Activity -->
        <activity
            android:name=".activities.FavoritesActivity"
            android:exported="false" />

        <!-- Profile Activity -->
        <activity
            android:name=".activities.ProfileActivity"
            android:exported="false" />

        <!-- Settings Activity -->
        <activity
            android:name=".activities.SettingsActivity"
            android:exported="false" />

    </application>

</manifest>
```

### colors.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Primary Colors (SDG 12 - Green Theme) -->
    <color name="primary">#4CAF50</color>
    <color name="primary_dark">#388E3C</color>
    <color name="primary_light">#C8E6C9</color>
    
    <!-- Secondary Colors -->
    <color name="secondary">#009688</color>
    <color name="secondary_dark">#00796B</color>
    <color name="secondary_light">#B2DFDB</color>
    
    <!-- Accent Colors -->
    <color name="accent">#FFC107</color>
    <color name="accent_dark">#FFA000</color>
    
    <!-- Background -->
    <color name="background">#FFFFFF</color>
    <color name="background_light">#F5F5F5</color>
    
    <!-- Text -->
    <color name="text_primary">#212121</color>
    <color name="text_secondary">#757575</color>
    <color name="text_hint">#BDBDBD</color>
    
    <!-- Status -->
    <color name="success">#4CAF50</color>
    <color name="error">#F44336</color>
    <color name="warning">#FF9800</color>
    
    <!-- Common -->
    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
    <color name="transparent">#00000000</color>
</resources>
```

### strings.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">Style Lens</string>
    
    <!-- Splash -->
    <string name="splash_tagline">AI-Powered Sustainable Styling</string>
    
    <!-- Login -->
    <string name="login_title">Welcome Back</string>
    <string name="email_hint">Email</string>
    <string name="password_hint">Password</string>
    <string name="login_button">Login</string>
    <string name="register_link">Don\'t have an account? Register</string>
    
    <!-- Register -->
    <string name="register_title">Create Account</string>
    <string name="name_hint">Full Name</string>
    <string name="register_button">Register</string>
    <string name="login_link">Already have an account? Login</string>
    
    <!-- Dashboard -->
    <string name="welcome_message">Hi, %s!</string>
    <string name="sustainability_tip">Sustainability Tip of the Day</string>
    <string name="quick_actions">Quick Style Actions</string>
    <string name="trending_palettes">Trending Palettes</string>
    <string name="ai_insights">AI Insights</string>
    <string name="recently_styled">Recently Styled</string>
    
    <!-- Navigation -->
    <string name="nav_dashboard">Dashboard</string>
    <string name="nav_people">People Style</string>
    <string name="nav_space">Space Style</string>
    <string name="nav_character">Character</string>
    <string name="nav_social">Social Media</string>
    <string name="nav_product">Products</string>
    <string name="nav_favorites">Favorites</string>
    <string name="nav_profile">Profile</string>
    
    <!-- Style Input -->
    <string name="body_type">Body Type</string>
    <string name="skin_tone">Skin Tone</string>
    <string name="occasion">Occasion</string>
    <string name="budget">Budget</string>
    <string name="generate_style">Generate Style</string>
    
    <!-- Menu -->
    <string name="menu_settings">Settings</string>
    <string name="menu_logout">Logout</string>
    <string name="menu_help">Help</string>
    <string name="menu_about">About</string>
    
    <!-- SDG -->
    <string name="sdg_message">Supporting SDG 12: Responsible Consumption</string>
</resources>
```

## 🎨 Key Implementation Files

This structure provides:
- ✅ **10 Activities** (all required)
- ✅ **6 Fragments** (mandatory)
- ✅ **5 Adapters** for RecyclerViews
- ✅ **MVVM Architecture** (Models, ViewModels, Repository)
- ✅ **Network Layer** (Retrofit, API Service)
- ✅ **AI Modules** (5 AI/ML implementations)
- ✅ **Utilities** (SharedPrefs, Constants, Validation)
- ✅ **Complete Layouts** (Activities, Fragments, Items)
- ✅ **All Menus** (Options, Context, Popup, Navigation)
- ✅ **Material Design** (Colors, Themes)

---

**Next Steps**:
1. Implement all activities and fragments
2. Create adapters for RecyclerViews
3. Set up network layer with Retrofit
4. Integrate AI modules
5. Connect to backend API

**Android Project Structure Version**: 1.0  
**Status**: ✅ READY FOR IMPLEMENTATION
