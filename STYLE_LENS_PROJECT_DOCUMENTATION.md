# STYLE LENS - Complete Project Documentation

## 🎯 PROJECT OVERVIEW

**STYLE LENS** is an AI-powered Android application developed using Kotlin, designed to provide intelligent styling recommendations for people, spaces, digital characters, products, and social media content. The application aligns with **SDG 12 – Responsible Consumption and Production**.

---

## 📱 APPLICATION ARCHITECTURE

### Technology Stack
- **Language**: Kotlin
- **Database**: MySQL
- **AI/ML**: Hybrid (Rule-Based + Machine Learning)
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Components**: Material Design 3

### Key Features
✅ Multi-domain styling (People, Spaces, Characters, Products, Social Media)
✅ AI-powered recommendations
✅ Sustainability scoring
✅ Personalized color palettes
✅ Budget-based styling
✅ Community features (favorites, ratings, sharing)

---

## 🏗️ APPLICATION MODULES

### Activities (10 Total)
1. **SplashActivity** - App launch screen with branding
2. **LoginActivity** - User authentication
3. **RegisterActivity** - New user registration
4. **HomeActivity** - Main navigation hub (contains fragments)
5. **StyleInputActivity** - User input for styling preferences
6. **StyleResultActivity** - Display AI-generated recommendations
7. **ProductCatalogActivity** - Browse sustainable products
8. **FavoritesActivity** - Saved styles
9. **ProfileActivity** - User profile management
10. **SettingsActivity** - App preferences

### Fragments (6 Total - Inside HomeActivity)
1. **DashboardFragment** - Central control hub
2. **PeopleStyleFragment** - Personal styling
3. **SpaceStyleFragment** - Room/interior styling
4. **CharacterStyleFragment** - Digital character design
5. **SocialMediaFragment** - Social media aesthetics
6. **ProductFragment** - Product styling

### Navigation
- **Bottom Navigation View** (Primary)
- **Navigation Drawer** (Secondary/Settings)

---

## 🎛️ INPUT CONTROLS (10 Total - Exceeds Requirement)

| Input Control | Usage | Activity/Fragment |
|--------------|-------|-------------------|
| **EditText** | Name, age, height, preferences | StyleInputActivity |
| **Spinner** | Occasion, body type, face shape, room type | StyleInputActivity |
| **RadioButton** | Gender, style intensity | StyleInputActivity |
| **CheckBox** | Accessories preference, sustainability options | StyleInputActivity |
| **SeekBar** | Budget range, boldness level | StyleInputActivity |
| **Switch** | Sustainable mode ON/OFF, dark mode | StyleInputActivity, SettingsActivity |
| **Button** | Generate Style, Save, Share | Multiple |
| **ImageView** | Upload photo (clickable) | StyleInputActivity |
| **RatingBar** | Feedback on styling results | StyleResultActivity |
| **AutoCompleteTextView** | Color preference input | StyleInputActivity |

### Top 4 Categories Covered ✅
- ✅ **Range** → SeekBar
- ✅ **Selection** → Spinner, RadioButton, CheckBox
- ✅ **Input Text** → EditText, AutoCompleteTextView
- ✅ **Button** → Button, ImageView (clickable)

---

## 🧱 LAYOUTS & VIEWS

### Layout Types
| Layout | Usage |
|--------|-------|
| **ConstraintLayout** | Main screens (modern, flexible) |
| **LinearLayout** | Forms, vertical/horizontal lists |
| **RelativeLayout** | Card layouts, overlays |
| **FrameLayout** | Fragment container in HomeActivity |
| **CoordinatorLayout** | Scrolling behaviors |

### Views (All Required ✅)
| View | Usage |
|------|-------|
| **ScrollView** | Long input forms |
| **Vertical ScrollView** | Style details, recommendations |
| **HorizontalScrollView** | Color palettes, product categories |
| **CardView** | Style cards, product cards |
| **RecyclerView** | Product lists, style history, trending palettes |
| **ImageView** | Outfit previews, product images |
| **TextView** | Descriptions, labels |
| **FloatingActionButton** | Quick actions |

---

## 🍔 MENUS & NAVIGATION (All 4 Types ✅)

### 1. Options Menu
- **Location**: Toolbar in all activities
- **Items**: Settings, Logout, Help, About

### 2. Context Menu
- **Trigger**: Long-press on style cards
- **Items**: Edit, Delete, Share, Set as Favorite

### 3. Popup Menu
- **Trigger**: Filter icon in ProductCatalogActivity
- **Items**: Sort by Price, Sort by Rating, Filter by Category

### 4. Navigation Menu
- **Type**: Bottom Navigation (Primary) + Navigation Drawer (Secondary)
- **Items**: 
  - Bottom Nav: Dashboard, Explore, Favorites, Profile
  - Drawer: Settings, Help, Feedback, About, Logout

---

## 🗄️ DATABASE DESIGN (MySQL)

### Database Name: `style_lens_db`

### Tables Overview (11 Tables)

#### 1. USER Table
```sql
CREATE TABLE USER (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    sustainability_preference BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### 2. STYLE_PROFILE Table
```sql
CREATE TABLE STYLE_PROFILE (
    profile_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    body_type VARCHAR(50),
    face_shape VARCHAR(50),
    skin_tone VARCHAR(50),
    hair_type VARCHAR(50),
    height DECIMAL(5,2),
    preferences TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE
);
```

#### 3. COLOR_PALETTE Table
```sql
CREATE TABLE COLOR_PALETTE (
    palette_id INT PRIMARY KEY AUTO_INCREMENT,
    palette_name VARCHAR(100) NOT NULL,
    primary_color VARCHAR(7),
    secondary_color VARCHAR(7),
    accent_color VARCHAR(7),
    usage_type VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### 4. STYLE_RESULT Table
```sql
CREATE TABLE STYLE_RESULT (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    profile_id INT NOT NULL,
    palette_id INT,
    style_type ENUM('people', 'space', 'character', 'product', 'social_media') NOT NULL,
    recommendation_text TEXT,
    sustainability_score INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (profile_id) REFERENCES STYLE_PROFILE(profile_id) ON DELETE CASCADE,
    FOREIGN KEY (palette_id) REFERENCES COLOR_PALETTE(palette_id) ON DELETE SET NULL
);
```

#### 5. PRODUCT Table
```sql
CREATE TABLE PRODUCT (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(100),
    name VARCHAR(200),
    color VARCHAR(50),
    price DECIMAL(10,2),
    sustainability_rating INT DEFAULT 0,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### 6. FAVORITE_STYLE Table
```sql
CREATE TABLE FAVORITE_STYLE (
    favorite_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    result_id INT NOT NULL,
    saved_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE,
    FOREIGN KEY (result_id) REFERENCES STYLE_RESULT(result_id) ON DELETE CASCADE
);
```

#### 7. FEEDBACK Table
```sql
CREATE TABLE FEEDBACK (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    result_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE,
    FOREIGN KEY (result_id) REFERENCES STYLE_RESULT(result_id) ON DELETE CASCADE
);
```

#### 8. SPACE_STYLE Table
```sql
CREATE TABLE SPACE_STYLE (
    space_id INT PRIMARY KEY AUTO_INCREMENT,
    room_type VARCHAR(100),
    size VARCHAR(50),
    lighting VARCHAR(50),
    mood VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### 9. AI_MODEL Table (AI/ML Integration)
```sql
CREATE TABLE AI_MODEL (
    model_id INT PRIMARY KEY AUTO_INCREMENT,
    model_type VARCHAR(100) NOT NULL,
    accuracy DECIMAL(5,2),
    version VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### 10. AI_PREDICTION Table (AI/ML Integration)
```sql
CREATE TABLE AI_PREDICTION (
    prediction_id INT PRIMARY KEY AUTO_INCREMENT,
    model_id INT NOT NULL,
    input_features TEXT,
    output_result TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (model_id) REFERENCES AI_MODEL(model_id) ON DELETE CASCADE
);
```

#### 11. SUSTAINABILITY_LOG Table
```sql
CREATE TABLE SUSTAINABILITY_LOG (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    action_type VARCHAR(100),
    score_change INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE
);
```

---

## 🔗 ER DIAGRAM (Entity-Relationship)

### Entities and Relationships

```
USER (1) ──────── (M) STYLE_PROFILE
  │                      │
  │                      │
  │                      └──── (M) STYLE_RESULT ──── (1) COLOR_PALETTE
  │                                    │
  │                                    └──── (M) PRODUCT (recommended)
  │
  ├──── (M) FAVORITE_STYLE ──── (1) STYLE_RESULT
  │
  ├──── (M) FEEDBACK ──── (1) STYLE_RESULT
  │
  └──── (M) SUSTAINABILITY_LOG

AI_MODEL (1) ──────── (M) AI_PREDICTION
                            │
                            └──── used_in ──── STYLE_RESULT

SPACE_STYLE ──── linked_to ──── STYLE_RESULT
```

### Cardinality Summary
- **One User** can have **Multiple Style Profiles**
- **One Style Profile** can generate **Multiple Style Results**
- **One Style Result** uses **One Color Palette**
- **One Style Result** can recommend **Multiple Products**
- **Users** can save **Multiple Favorite Styles**
- **Users** can provide **Multiple Feedbacks**
- **One AI Model** generates **Multiple AI Predictions**
- **AI Predictions** are used in **Style Results**

---

## 🤖 AI & ML INTEGRATION

### Hybrid AI Architecture

```
┌─────────────────────────────────────┐
│     USER INPUT LAYER                │
│  (Body type, preferences, budget)   │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│   PREPROCESSING LAYER (Kotlin)      │
│  - Data validation                  │
│  - Feature extraction               │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│     AI LOGIC LAYER                  │
│  ┌─────────────┬─────────────┐      │
│  │ Rule-Based  │  ML Models  │      │
│  │    AI       │             │      │
│  └─────────────┴─────────────┘      │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│   RECOMMENDATION ENGINE             │
│  - Style suggestions                │
│  - Color palettes                   │
│  - Product recommendations          │
│  - Sustainability scoring           │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│   RESULT DISPLAY (RecyclerView)     │
└─────────────────────────────────────┘
```

### AI Modules

#### 1. Personal Styling Intelligence
- **Type**: Rule-Based + ML
- **Algorithm**: Decision Tree + K-Means Clustering
- **Input**: Body type, skin tone, occasion, budget
- **Output**: Outfit combination, color palette, accessories

#### 2. Color Harmony ML Model
- **Type**: Classification Model
- **Algorithm**: K-Means Clustering
- **Purpose**: Match colors to skin tone and mood
- **Output**: Best palette (Earth Luxe, Neo Modern, Vibrant)

#### 3. Sustainability Score Prediction
- **Type**: Regression Model
- **Algorithm**: Linear Regression
- **Input**: Reused items, material type, product rating
- **Output**: Sustainability Score (0-100)

#### 4. Recommendation Engine
- **Type**: Collaborative Filtering
- **Algorithm**: Cosine Similarity
- **Purpose**: Recommend styles based on similar users

#### 5. Mood-Based Styling
- **Type**: NLP + Classification
- **Input**: User mood text
- **Processing**: Keyword extraction, sentiment analysis
- **Output**: Mood-appropriate palettes and styles

### ML Dataset Design

#### Training Dataset: style_dataset
| Feature | Type |
|---------|------|
| body_type | Categorical |
| skin_tone | Categorical |
| occasion | Categorical |
| budget | Numeric |
| palette | Label |
| outfit_type | Label |

#### Sustainability Dataset
| Feature | Type |
|---------|------|
| product_type | Categorical |
| material | Categorical |
| reuse_count | Numeric |
| eco_rating | Numeric |
| sustainability_score | Label |

---

## 🌍 SDG 12 IMPLEMENTATION

### Alignment with Responsible Consumption and Production

| AI Feature | SDG 12 Impact |
|------------|---------------|
| Reuse prediction | Reduces waste by suggesting outfit reuse |
| Sustainability score | Increases awareness of consumption patterns |
| Budget optimization | Prevents unnecessary purchases |
| Color matching | Maximizes existing wardrobe usage |
| Eco-friendly product suggestions | Promotes sustainable alternatives |

### Key Sustainability Features
1. **Outfit Reuse Tracker** - Encourages wearing existing items
2. **Sustainability Score Dashboard** - Gamifies responsible consumption
3. **Eco-Product Recommendations** - Highlights sustainable options
4. **Budget Alerts** - Prevents overconsumption
5. **Material Impact Info** - Educates about environmental impact

---

## 📊 DASHBOARD DESIGN

### Dashboard Components (DashboardFragment)

```
┌─────────────────────────────────────┐
│  Welcome Card                       │
│  👋 Hi, [User Name]                 │
│  🌱 Sustainability Tip of the Day   │
└─────────────────────────────────────┘

┌─────────────────────────────────────┐
│  Quick Style Actions                │
│  (Horizontal RecyclerView)          │
│  ┌───┐ ┌───┐ ┌───┐ ┌───┐ ┌───┐    │
│  │👗│ │🏠│ │🎭│ │📦│ │📸│    │
│  └───┘ └───┘ └───┘ └───┘ └───┘    │
└─────────────────────────────────────┘

┌─────────────────────────────────────┐
│  Trending Palettes                  │
│  (Horizontal ScrollView)            │
│  ┌─────┐ ┌─────┐ ┌─────┐           │
│  │Earth│ │Neo  │ │Vibr.│           │
│  │Luxe │ │Mod. │ │Street│          │
│  └─────┘ └─────┘ └─────┘           │
└─────────────────────────────────────┘

┌─────────────────────────────────────┐
│  AI Insights Card                   │
│  🌱 You reused 3 outfits this month │
│  📊 Sustainability Score: 78%       │
│  🎯 Goal: Reach 85% this month      │
└─────────────────────────────────────┘

┌─────────────────────────────────────┐
│  Recently Styled Items              │
│  (Vertical RecyclerView)            │
│  ┌─────────────────────────────┐   │
│  │ Formal Office Look          │   │
│  │ ⭐⭐⭐⭐⭐ | 2 days ago      │   │
│  └─────────────────────────────┘   │
│  ┌─────────────────────────────┐   │
│  │ Cozy Living Room            │   │
│  │ ⭐⭐⭐⭐ | 5 days ago        │   │
│  └─────────────────────────────┘   │
└─────────────────────────────────────┘
```

---

## 🔀 NAVIGATION FLOW

### Activity Flow Diagram

```
SplashActivity
      │
      ▼
LoginActivity ──────► RegisterActivity
      │                     │
      └──────────┬──────────┘
                 ▼
          HomeActivity
          (Bottom Nav)
      ┌───────┴───────┐
      │  Fragments:   │
      │  - Dashboard  │
      │  - People     │
      │  - Space      │
      │  - Character  │
      │  - Social     │
      │  - Product    │
      └───────┬───────┘
              │
    ┌─────────┼─────────┬─────────┐
    ▼         ▼         ▼         ▼
StyleInput  Product  Favorites  Profile
Activity   Catalog   Activity  Activity
    │      Activity      │         │
    ▼                    │         │
StyleResult ─────────────┘         │
Activity                           │
                                   ▼
                            SettingsActivity
```

### Intent Usage
- Login → Home (Explicit Intent)
- Dashboard → Style Input (Explicit Intent)
- Style Result → Product Catalog (Explicit Intent)
- Product → Share (Implicit Intent - ACTION_SEND)
- Settings → Logout → Login (Explicit Intent with FLAG_CLEAR_TOP)

---

## 📱 RECYCLERVIEW IMPLEMENTATIONS

### RecyclerView Usage (5+ Instances)

1. **Dashboard Quick Actions** (Horizontal)
   - Adapter: `QuickActionAdapter`
   - Layout: `item_quick_action.xml`

2. **Trending Palettes** (Horizontal)
   - Adapter: `PaletteAdapter`
   - Layout: `item_palette.xml`

3. **Recently Styled Items** (Vertical)
   - Adapter: `StyleHistoryAdapter`
   - Layout: `item_style_history.xml`

4. **Product Catalog** (Grid)
   - Adapter: `ProductAdapter`
   - Layout: `item_product.xml`

5. **Favorites List** (Vertical)
   - Adapter: `FavoritesAdapter`
   - Layout: `item_favorite.xml`

---

## 🎤 VIVA PREPARATION

### Key Questions & Answers

#### Q1: What is the main objective of STYLE LENS?
**A**: STYLE LENS is an AI-powered Android application that provides intelligent styling recommendations across multiple domains (people, spaces, characters, products, social media) while promoting sustainable consumption aligned with SDG 12.

#### Q2: How many activities and fragments does your app have?
**A**: The app has 10 activities and 6 fragments. Fragments are used inside HomeActivity for modular navigation using Bottom Navigation View.

#### Q3: Which input controls have you used?
**A**: I've used 10 input controls: EditText, Spinner, RadioButton, CheckBox, SeekBar, Switch, Button, ImageView (clickable), RatingBar, and AutoCompleteTextView, covering all four categories (Range, Selection, Input Text, Button).

#### Q4: Explain your database design.
**A**: The database has 11 tables including USER, STYLE_PROFILE, COLOR_PALETTE, STYLE_RESULT, PRODUCT, FAVORITE_STYLE, FEEDBACK, SPACE_STYLE, AI_MODEL, AI_PREDICTION, and SUSTAINABILITY_LOG. The design follows normalization principles with proper foreign key relationships.

#### Q5: How does AI work in your application?
**A**: STYLE LENS uses a hybrid AI system combining rule-based logic and machine learning models. It includes:
- Personal styling intelligence using decision trees
- Color harmony using K-Means clustering
- Sustainability score prediction using regression
- Recommendation engine using collaborative filtering
- Mood-based styling using NLP

#### Q6: How does your app support SDG 12?
**A**: The app promotes responsible consumption by:
- Tracking outfit reuse
- Calculating sustainability scores
- Recommending eco-friendly products
- Optimizing budget to prevent overconsumption
- Educating users about material impact

#### Q7: What menu types have you implemented?
**A**: All four menu types:
- Options Menu (Settings, Logout)
- Context Menu (Long-press on style cards)
- Popup Menu (Filter/Sort options)
- Navigation Menu (Bottom Nav + Drawer)

#### Q8: Explain your RecyclerView usage.
**A**: RecyclerView is used in 5+ places:
- Dashboard quick actions (horizontal)
- Trending palettes (horizontal)
- Style history (vertical)
- Product catalog (grid)
- Favorites list (vertical)

#### Q9: What layouts have you used?
**A**: ConstraintLayout (main screens), LinearLayout (forms), RelativeLayout (cards), FrameLayout (fragment container), and CoordinatorLayout (scrolling behaviors).

#### Q10: How is ML integrated in the Android app?
**A**: Currently, ML predictions are simulated using trained datasets with JSON responses. The architecture supports real-time model integration. Preprocessing is done in Kotlin, and predictions are displayed via RecyclerView.

---

## 📄 PROJECT SUMMARY (One-Liner)

**STYLE LENS is an AI-powered Android application developed using Kotlin, featuring multiple activities, fragments, RecyclerViews, MySQL database integration, hybrid AI/ML models, and alignment with SDG 12 to promote responsible consumption through intelligent styling recommendations across people, spaces, characters, products, and social media.**

---

## ✅ REQUIREMENTS CHECKLIST

- ✅ **Activities**: 10 (Exceeds requirement)
- ✅ **Fragments**: 6 (Mandatory requirement met)
- ✅ **Input Controls**: 10 (Exceeds 8 minimum)
- ✅ **Layouts**: 5 types
- ✅ **Views**: All required (ScrollView, RecyclerView, CardView, etc.)
- ✅ **Menus**: All 4 types
- ✅ **Database**: MySQL with 11 tables
- ✅ **RecyclerView**: 5+ implementations
- ✅ **AI/ML**: Hybrid system with 5 modules
- ✅ **SDG**: SDG 12 alignment
- ✅ **Intents**: Explicit and Implicit

---

**Project Status**: ✅ COMPLETE & VIVA-READY

**Documentation Version**: 1.0
**Last Updated**: February 2, 2026
