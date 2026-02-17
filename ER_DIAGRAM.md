# STYLE LENS - Entity-Relationship Diagram

## 📊 COMPLETE ER DIAGRAM

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          STYLE LENS DATABASE SCHEMA                         │
└─────────────────────────────────────────────────────────────────────────────┘

┌──────────────────┐
│      USER        │
├──────────────────┤
│ PK user_id       │
│    name          │
│    email         │
│    password      │
│    sustainability│
│    _preference   │
│    created_at    │
│    updated_at    │
└────────┬─────────┘
         │
         │ 1:M (One user has many profiles)
         │
         ▼
┌──────────────────┐
│ STYLE_PROFILE    │
├──────────────────┤
│ PK profile_id    │
│ FK user_id       │────────┐
│    body_type     │        │
│    face_shape    │        │
│    skin_tone     │        │
│    hair_type     │        │
│    height        │        │
│    preferences   │        │
│    created_at    │        │
└────────┬─────────┘        │
         │                  │
         │ 1:M (One profile generates many results)
         │                  │
         ▼                  │
┌──────────────────┐        │
│  STYLE_RESULT    │        │
├──────────────────┤        │
│ PK result_id     │        │
│ FK profile_id    │────────┘
│ FK palette_id    │────────┐
│    style_type    │        │
│    recommend_    │        │
│    ation_text    │        │
│    sustainability│        │
│    _score        │        │
│    created_at    │        │
└────────┬─────────┘        │
         │                  │
         │                  │ M:1 (Many results use one palette)
         │                  │
         │                  ▼
         │         ┌──────────────────┐
         │         │  COLOR_PALETTE   │
         │         ├──────────────────┤
         │         │ PK palette_id    │
         │         │    palette_name  │
         │         │    primary_color │
         │         │    secondary_    │
         │         │    color         │
         │         │    accent_color  │
         │         │    usage_type    │
         │         │    created_at    │
         │         └──────────────────┘
         │
         │ M:M (Results recommend many products)
         │
         ▼
┌──────────────────┐
│    PRODUCT       │
├──────────────────┤
│ PK product_id    │
│    category      │
│    name          │
│    color         │
│    price         │
│    sustainability│
│    _rating       │
│    image_url     │
│    created_at    │
└──────────────────┘


┌──────────────────┐
│      USER        │
│ PK user_id       │
└────────┬─────────┘
         │
         │ 1:M (User saves many favorites)
         │
         ▼
┌──────────────────┐
│ FAVORITE_STYLE   │
├──────────────────┤
│ PK favorite_id   │
│ FK user_id       │
│ FK result_id     │────► STYLE_RESULT
│    saved_date    │
└──────────────────┘


┌──────────────────┐
│      USER        │
│ PK user_id       │
└────────┬─────────┘
         │
         │ 1:M (User provides many feedbacks)
         │
         ▼
┌──────────────────┐
│    FEEDBACK      │
├──────────────────┤
│ PK feedback_id   │
│ FK user_id       │
│ FK result_id     │────► STYLE_RESULT
│    rating        │
│    comment       │
│    created_at    │
└──────────────────┘


┌──────────────────┐
│   SPACE_STYLE    │
├──────────────────┤
│ PK space_id      │
│    room_type     │
│    size          │
│    lighting      │
│    mood          │
│    created_at    │
└────────┬─────────┘
         │
         │ 1:1 (Linked to style result)
         │
         └────────────► STYLE_RESULT


┌──────────────────┐
│    AI_MODEL      │
├──────────────────┤
│ PK model_id      │
│    model_type    │
│    accuracy      │
│    version       │
│    created_at    │
└────────┬─────────┘
         │
         │ 1:M (One model generates many predictions)
         │
         ▼
┌──────────────────┐
│  AI_PREDICTION   │
├──────────────────┤
│ PK prediction_id │
│ FK model_id      │
│    input_        │
│    features      │
│    output_result │
│    created_at    │
└────────┬─────────┘
         │
         │ M:1 (Predictions used in results)
         │
         └────────────► STYLE_RESULT


┌──────────────────┐
│      USER        │
│ PK user_id       │
└────────┬─────────┘
         │
         │ 1:M (User has sustainability logs)
         │
         ▼
┌──────────────────┐
│ SUSTAINABILITY_  │
│      LOG         │
├──────────────────┤
│ PK log_id        │
│ FK user_id       │
│    action_type   │
│    score_change  │
│    created_at    │
└──────────────────┘
```

---

## 🔗 RELATIONSHIP SUMMARY

### Primary Relationships

| From Entity | Relationship | To Entity | Cardinality | Description |
|-------------|--------------|-----------|-------------|-------------|
| USER | creates | STYLE_PROFILE | 1:M | One user can have multiple style profiles |
| STYLE_PROFILE | generates | STYLE_RESULT | 1:M | One profile can generate multiple style results |
| STYLE_RESULT | uses | COLOR_PALETTE | M:1 | Many results can use one color palette |
| STYLE_RESULT | recommends | PRODUCT | M:M | Results can recommend multiple products |
| USER | saves | FAVORITE_STYLE | 1:M | User can save multiple favorite styles |
| FAVORITE_STYLE | references | STYLE_RESULT | M:1 | Multiple favorites can reference one result |
| USER | provides | FEEDBACK | 1:M | User can provide multiple feedbacks |
| FEEDBACK | rates | STYLE_RESULT | M:1 | Multiple feedbacks can rate one result |
| SPACE_STYLE | linked_to | STYLE_RESULT | 1:1 | Space style linked to one result |
| AI_MODEL | generates | AI_PREDICTION | 1:M | One model generates multiple predictions |
| AI_PREDICTION | used_in | STYLE_RESULT | M:1 | Multiple predictions used in results |
| USER | tracks | SUSTAINABILITY_LOG | 1:M | User has multiple sustainability logs |

---

## 📋 ENTITY DETAILS

### 1. USER Entity
**Purpose**: Stores user account information

**Attributes**:
- `user_id` (PK) - Unique identifier
- `name` - User's full name
- `email` - Login email (unique)
- `password` - Encrypted password
- `sustainability_preference` - Boolean flag for eco-mode
- `created_at` - Account creation timestamp
- `updated_at` - Last modification timestamp

**Relationships**:
- Creates → STYLE_PROFILE (1:M)
- Saves → FAVORITE_STYLE (1:M)
- Provides → FEEDBACK (1:M)
- Tracks → SUSTAINABILITY_LOG (1:M)

---

### 2. STYLE_PROFILE Entity
**Purpose**: Stores user's physical and style characteristics

**Attributes**:
- `profile_id` (PK) - Unique identifier
- `user_id` (FK) - References USER
- `body_type` - e.g., Pear, Apple, Hourglass
- `face_shape` - e.g., Oval, Round, Square
- `skin_tone` - e.g., Fair, Medium, Dark
- `hair_type` - e.g., Straight, Wavy, Curly
- `height` - In centimeters
- `preferences` - JSON/Text of style preferences
- `created_at` - Profile creation timestamp

**Relationships**:
- Belongs to → USER (M:1)
- Generates → STYLE_RESULT (1:M)

---

### 3. STYLE_RESULT Entity
**Purpose**: Stores AI-generated styling recommendations

**Attributes**:
- `result_id` (PK) - Unique identifier
- `profile_id` (FK) - References STYLE_PROFILE
- `palette_id` (FK) - References COLOR_PALETTE
- `style_type` - ENUM: people, space, character, product, social_media
- `recommendation_text` - Detailed styling advice
- `sustainability_score` - Score 0-100
- `created_at` - Result generation timestamp

**Relationships**:
- Generated by → STYLE_PROFILE (M:1)
- Uses → COLOR_PALETTE (M:1)
- Recommends → PRODUCT (M:M)
- Referenced by → FAVORITE_STYLE (1:M)
- Rated by → FEEDBACK (1:M)
- Linked to → SPACE_STYLE (1:1)
- Uses → AI_PREDICTION (M:1)

---

### 4. COLOR_PALETTE Entity
**Purpose**: Stores predefined color schemes

**Attributes**:
- `palette_id` (PK) - Unique identifier
- `palette_name` - e.g., "Earth Luxe", "Neo Modern"
- `primary_color` - Hex code (e.g., #2C3E50)
- `secondary_color` - Hex code
- `accent_color` - Hex code
- `usage_type` - e.g., Formal, Casual, Bold
- `created_at` - Palette creation timestamp

**Relationships**:
- Used by → STYLE_RESULT (1:M)

---

### 5. PRODUCT Entity
**Purpose**: Stores sustainable product catalog

**Attributes**:
- `product_id` (PK) - Unique identifier
- `category` - e.g., Clothing, Furniture, Accessories
- `name` - Product name
- `color` - Primary color
- `price` - Decimal value
- `sustainability_rating` - Score 0-100
- `image_url` - Product image path
- `created_at` - Product addition timestamp

**Relationships**:
- Recommended by → STYLE_RESULT (M:M)

---

### 6. FAVORITE_STYLE Entity
**Purpose**: Stores user's saved/favorite styles

**Attributes**:
- `favorite_id` (PK) - Unique identifier
- `user_id` (FK) - References USER
- `result_id` (FK) - References STYLE_RESULT
- `saved_date` - Timestamp of saving

**Relationships**:
- Belongs to → USER (M:1)
- References → STYLE_RESULT (M:1)

---

### 7. FEEDBACK Entity
**Purpose**: Stores user ratings and comments on style results

**Attributes**:
- `feedback_id` (PK) - Unique identifier
- `user_id` (FK) - References USER
- `result_id` (FK) - References STYLE_RESULT
- `rating` - Integer 1-5
- `comment` - Text feedback
- `created_at` - Feedback timestamp

**Relationships**:
- Provided by → USER (M:1)
- Rates → STYLE_RESULT (M:1)

---

### 8. SPACE_STYLE Entity
**Purpose**: Stores room/space-specific styling parameters

**Attributes**:
- `space_id` (PK) - Unique identifier
- `room_type` - e.g., Living Room, Bedroom
- `size` - e.g., Small, Medium, Large
- `lighting` - e.g., Natural, Artificial, Mixed
- `mood` - e.g., Cozy, Modern, Minimalist
- `created_at` - Record creation timestamp

**Relationships**:
- Linked to → STYLE_RESULT (1:1)

---

### 9. AI_MODEL Entity
**Purpose**: Stores AI/ML model metadata

**Attributes**:
- `model_id` (PK) - Unique identifier
- `model_type` - e.g., Color Harmony, Sustainability Predictor
- `accuracy` - Model accuracy percentage
- `version` - Model version number
- `created_at` - Model creation timestamp

**Relationships**:
- Generates → AI_PREDICTION (1:M)

---

### 10. AI_PREDICTION Entity
**Purpose**: Stores AI model predictions and inputs

**Attributes**:
- `prediction_id` (PK) - Unique identifier
- `model_id` (FK) - References AI_MODEL
- `input_features` - JSON of input data
- `output_result` - JSON of prediction result
- `created_at` - Prediction timestamp

**Relationships**:
- Generated by → AI_MODEL (M:1)
- Used in → STYLE_RESULT (M:1)

---

### 11. SUSTAINABILITY_LOG Entity
**Purpose**: Tracks user's sustainability actions and score changes

**Attributes**:
- `log_id` (PK) - Unique identifier
- `user_id` (FK) - References USER
- `action_type` - e.g., "Reused outfit", "Chose eco-product"
- `score_change` - Positive/negative score impact
- `created_at` - Action timestamp

**Relationships**:
- Belongs to → USER (M:1)

---

## 🎯 NORMALIZATION ANALYSIS

### First Normal Form (1NF) ✅
- All tables have atomic values
- No repeating groups
- Each column contains single values

### Second Normal Form (2NF) ✅
- All non-key attributes fully depend on primary key
- No partial dependencies

### Third Normal Form (3NF) ✅
- No transitive dependencies
- All attributes depend only on primary key

---

## 🔐 CONSTRAINTS & INTEGRITY

### Primary Keys
- Every table has a unique primary key
- Auto-increment for all IDs

### Foreign Keys
- All relationships enforced with foreign key constraints
- CASCADE DELETE where appropriate (user deletion removes related data)
- SET NULL for optional relationships (palette deletion doesn't delete results)

### Check Constraints
- `rating` in FEEDBACK: CHECK (rating BETWEEN 1 AND 5)
- `sustainability_score`: CHECK (sustainability_score BETWEEN 0 AND 100)
- `style_type`: ENUM constraint

### Unique Constraints
- `email` in USER table must be unique

---

## 📊 INDEXES (For Performance)

```sql
-- User table
CREATE INDEX idx_user_email ON USER(email);

-- Style Profile
CREATE INDEX idx_profile_user ON STYLE_PROFILE(user_id);

-- Style Result
CREATE INDEX idx_result_profile ON STYLE_RESULT(profile_id);
CREATE INDEX idx_result_palette ON STYLE_RESULT(palette_id);
CREATE INDEX idx_result_type ON STYLE_RESULT(style_type);

-- Favorite Style
CREATE INDEX idx_favorite_user ON FAVORITE_STYLE(user_id);
CREATE INDEX idx_favorite_result ON FAVORITE_STYLE(result_id);

-- Feedback
CREATE INDEX idx_feedback_user ON FEEDBACK(user_id);
CREATE INDEX idx_feedback_result ON FEEDBACK(result_id);

-- AI Prediction
CREATE INDEX idx_prediction_model ON AI_PREDICTION(model_id);

-- Sustainability Log
CREATE INDEX idx_log_user ON SUSTAINABILITY_LOG(user_id);
```

---

## 🎤 VIVA QUESTIONS ON ER DIAGRAM

### Q1: How many entities are in your ER diagram?
**A**: There are 11 entities: USER, STYLE_PROFILE, STYLE_RESULT, COLOR_PALETTE, PRODUCT, FAVORITE_STYLE, FEEDBACK, SPACE_STYLE, AI_MODEL, AI_PREDICTION, and SUSTAINABILITY_LOG.

### Q2: Explain the relationship between USER and STYLE_PROFILE.
**A**: It's a one-to-many relationship. One user can create multiple style profiles (e.g., casual profile, formal profile), but each profile belongs to only one user. The foreign key `user_id` in STYLE_PROFILE references the primary key in USER.

### Q3: Why is there a separate COLOR_PALETTE table?
**A**: To avoid redundancy and maintain consistency. Multiple style results can use the same color palette. This follows database normalization principles and makes it easy to update palettes centrally.

### Q4: What is the cardinality between STYLE_RESULT and PRODUCT?
**A**: It's a many-to-many relationship. One style result can recommend multiple products, and one product can be recommended in multiple style results. This would typically require a junction table in implementation.

### Q5: How does AI integration reflect in the ER diagram?
**A**: Through AI_MODEL and AI_PREDICTION entities. AI_MODEL stores model metadata (type, accuracy, version), and AI_PREDICTION stores individual predictions. Predictions are linked to STYLE_RESULT to show which AI model generated which recommendation.

### Q6: What is the purpose of SUSTAINABILITY_LOG?
**A**: To track user actions related to sustainability (outfit reuse, eco-product selection) and calculate cumulative sustainability scores over time. This supports SDG 12 by gamifying responsible consumption.

### Q7: Is your database normalized?
**A**: Yes, it follows Third Normal Form (3NF). All tables have atomic values, no partial dependencies, and no transitive dependencies. This ensures data integrity and reduces redundancy.

### Q8: What happens if a user is deleted?
**A**: Due to CASCADE DELETE constraints, all related data (style profiles, favorites, feedback, sustainability logs) are automatically deleted. This maintains referential integrity.

---

**ER Diagram Version**: 1.0  
**Last Updated**: February 2, 2026  
**Status**: ✅ COMPLETE & VIVA-READY
