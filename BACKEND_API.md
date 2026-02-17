# STYLE LENS - Backend API (Node.js + Express)

## 🚀 Backend Project Structure

```
StyleLensBackend/
├── src/
│   ├── config/
│   │   ├── database.js
│   │   └── config.js
│   ├── controllers/
│   │   ├── authController.js
│   │   ├── styleController.js
│   │   ├── productController.js
│   │   ├── profileController.js
│   │   └── aiController.js
│   ├── models/
│   │   ├── User.js
│   │   ├── StyleProfile.js
│   │   ├── StyleResult.js
│   │   ├── Product.js
│   │   └── ColorPalette.js
│   ├── routes/
│   │   ├── authRoutes.js
│   │   ├── styleRoutes.js
│   │   ├── productRoutes.js
│   │   ├── profileRoutes.js
│   │   └── aiRoutes.js
│   ├── middleware/
│   │   ├── auth.js
│   │   ├── errorHandler.js
│   │   └── validator.js
│   ├── services/
│   │   ├── aiService.js
│   │   ├── sustainabilityService.js
│   │   └── recommendationService.js
│   └── app.js
├── package.json
├── .env
└── README.md
```

## 📦 package.json

```json
{
  "name": "stylelens-backend",
  "version": "1.0.0",
  "description": "Backend API for Style Lens - AI-Powered Sustainable Styling App",
  "main": "src/app.js",
  "scripts": {
    "start": "node src/app.js",
    "dev": "nodemon src/app.js",
    "test": "jest"
  },
  "keywords": ["style", "ai", "sustainability", "fashion", "sdg12"],
  "author": "Your Name",
  "license": "MIT",
  "dependencies": {
    "express": "^4.18.2",
    "mysql2": "^3.6.5",
    "bcryptjs": "^2.4.3",
    "jsonwebtoken": "^9.0.2",
    "dotenv": "^16.3.1",
    "cors": "^2.8.5",
    "body-parser": "^1.20.2",
    "express-validator": "^7.0.1",
    "multer": "^1.4.5-lts.1"
  },
  "devDependencies": {
    "nodemon": "^3.0.2",
    "jest": "^29.7.0"
  }
}
```

## 🔧 .env Configuration

```env
# Server Configuration
PORT=3000
NODE_ENV=development

# Database Configuration
DB_HOST=localhost
DB_USER=root
DB_PASSWORD=your_password
DB_NAME=style_lens_db
DB_PORT=3306

# JWT Configuration
JWT_SECRET=your_super_secret_jwt_key_change_this_in_production
JWT_EXPIRE=7d

# API Configuration
API_VERSION=v1
BASE_URL=http://localhost:3000
```

## 🗄️ Database Configuration

### src/config/database.js
```javascript
const mysql = require('mysql2/promise');
require('dotenv').config();

const pool = mysql.createPool({
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    port: process.env.DB_PORT,
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0
});

// Test database connection
pool.getConnection()
    .then(connection => {
        console.log('✅ Database connected successfully');
        connection.release();
    })
    .catch(err => {
        console.error('❌ Database connection failed:', err.message);
    });

module.exports = pool;
```

### src/config/config.js
```javascript
module.exports = {
    port: process.env.PORT || 3000,
    jwtSecret: process.env.JWT_SECRET,
    jwtExpire: process.env.JWT_EXPIRE || '7d',
    apiVersion: process.env.API_VERSION || 'v1',
    baseUrl: process.env.BASE_URL || 'http://localhost:3000'
};
```

## 🔐 Authentication Middleware

### src/middleware/auth.js
```javascript
const jwt = require('jsonwebtoken');
const config = require('../config/config');

const authMiddleware = async (req, res, next) => {
    try {
        // Get token from header
        const token = req.header('Authorization')?.replace('Bearer ', '');

        if (!token) {
            return res.status(401).json({
                success: false,
                message: 'No authentication token, access denied'
            });
        }

        // Verify token
        const decoded = jwt.verify(token, config.jwtSecret);
        req.user = decoded;
        next();
    } catch (error) {
        res.status(401).json({
            success: false,
            message: 'Token is not valid'
        });
    }
};

module.exports = authMiddleware;
```

### src/middleware/errorHandler.js
```javascript
const errorHandler = (err, req, res, next) => {
    console.error(err.stack);

    res.status(err.statusCode || 500).json({
        success: false,
        message: err.message || 'Internal Server Error',
        error: process.env.NODE_ENV === 'development' ? err : {}
    });
};

module.exports = errorHandler;
```

## 🎯 Controllers

### src/controllers/authController.js
```javascript
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const db = require('../config/database');
const config = require('../config/config');

// Register new user
exports.register = async (req, res) => {
    try {
        const { name, email, password, sustainability_preference } = req.body;

        // Check if user exists
        const [existingUser] = await db.query(
            'SELECT * FROM USER WHERE email = ?',
            [email]
        );

        if (existingUser.length > 0) {
            return res.status(400).json({
                success: false,
                message: 'User already exists with this email'
            });
        }

        // Hash password
        const hashedPassword = await bcrypt.hash(password, 10);

        // Insert user
        const [result] = await db.query(
            'INSERT INTO USER (name, email, password, sustainability_preference) VALUES (?, ?, ?, ?)',
            [name, email, hashedPassword, sustainability_preference || true]
        );

        // Generate JWT token
        const token = jwt.sign(
            { userId: result.insertId, email },
            config.jwtSecret,
            { expiresIn: config.jwtExpire }
        );

        res.status(201).json({
            success: true,
            message: 'User registered successfully',
            data: {
                userId: result.insertId,
                name,
                email,
                token
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Registration failed',
            error: error.message
        });
    }
};

// Login user
exports.login = async (req, res) => {
    try {
        const { email, password } = req.body;

        // Find user
        const [users] = await db.query(
            'SELECT * FROM USER WHERE email = ?',
            [email]
        );

        if (users.length === 0) {
            return res.status(401).json({
                success: false,
                message: 'Invalid email or password'
            });
        }

        const user = users[0];

        // Check password
        const isPasswordValid = await bcrypt.compare(password, user.password);

        if (!isPasswordValid) {
            return res.status(401).json({
                success: false,
                message: 'Invalid email or password'
            });
        }

        // Generate JWT token
        const token = jwt.sign(
            { userId: user.user_id, email: user.email },
            config.jwtSecret,
            { expiresIn: config.jwtExpire }
        );

        res.status(200).json({
            success: true,
            message: 'Login successful',
            data: {
                userId: user.user_id,
                name: user.name,
                email: user.email,
                sustainability_preference: user.sustainability_preference,
                token
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Login failed',
            error: error.message
        });
    }
};

// Get current user
exports.getCurrentUser = async (req, res) => {
    try {
        const [users] = await db.query(
            'SELECT user_id, name, email, sustainability_preference, created_at FROM USER WHERE user_id = ?',
            [req.user.userId]
        );

        if (users.length === 0) {
            return res.status(404).json({
                success: false,
                message: 'User not found'
            });
        }

        res.status(200).json({
            success: true,
            data: users[0]
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to get user',
            error: error.message
        });
    }
};
```

### src/controllers/styleController.js
```javascript
const db = require('../config/database');
const aiService = require('../services/aiService');

// Create style profile
exports.createStyleProfile = async (req, res) => {
    try {
        const { body_type, face_shape, skin_tone, hair_type, height, preferences } = req.body;
        const userId = req.user.userId;

        const [result] = await db.query(
            'INSERT INTO STYLE_PROFILE (user_id, body_type, face_shape, skin_tone, hair_type, height, preferences) VALUES (?, ?, ?, ?, ?, ?, ?)',
            [userId, body_type, face_shape, skin_tone, hair_type, height, preferences]
        );

        res.status(201).json({
            success: true,
            message: 'Style profile created successfully',
            data: {
                profileId: result.insertId
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to create style profile',
            error: error.message
        });
    }
};

// Generate style recommendation
exports.generateStyleRecommendation = async (req, res) => {
    try {
        const { profileId, styleType, occasion, budget } = req.body;

        // Get profile data
        const [profiles] = await db.query(
            'SELECT * FROM STYLE_PROFILE WHERE profile_id = ?',
            [profileId]
        );

        if (profiles.length === 0) {
            return res.status(404).json({
                success: false,
                message: 'Style profile not found'
            });
        }

        const profile = profiles[0];

        // Generate AI recommendation
        const recommendation = await aiService.generateRecommendation({
            bodyType: profile.body_type,
            skinTone: profile.skin_tone,
            occasion,
            budget,
            preferences: JSON.parse(profile.preferences || '[]')
        });

        // Get color palette
        const [palettes] = await db.query(
            'SELECT * FROM COLOR_PALETTE WHERE palette_name = ?',
            [recommendation.colorPalette]
        );

        const paletteId = palettes.length > 0 ? palettes[0].palette_id : null;

        // Save style result
        const [result] = await db.query(
            'INSERT INTO STYLE_RESULT (profile_id, palette_id, style_type, recommendation_text, sustainability_score) VALUES (?, ?, ?, ?, ?)',
            [profileId, paletteId, styleType, JSON.stringify(recommendation), recommendation.sustainabilityScore]
        );

        res.status(201).json({
            success: true,
            message: 'Style recommendation generated successfully',
            data: {
                resultId: result.insertId,
                recommendation
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to generate recommendation',
            error: error.message
        });
    }
};

// Get style history
exports.getStyleHistory = async (req, res) => {
    try {
        const userId = req.user.userId;

        const [results] = await db.query(`
            SELECT sr.*, cp.palette_name, cp.primary_color, cp.secondary_color, cp.accent_color
            FROM STYLE_RESULT sr
            JOIN STYLE_PROFILE sp ON sr.profile_id = sp.profile_id
            LEFT JOIN COLOR_PALETTE cp ON sr.palette_id = cp.palette_id
            WHERE sp.user_id = ?
            ORDER BY sr.created_at DESC
            LIMIT 20
        `, [userId]);

        res.status(200).json({
            success: true,
            data: results
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to get style history',
            error: error.message
        });
    }
};

// Add to favorites
exports.addToFavorites = async (req, res) => {
    try {
        const { resultId } = req.body;
        const userId = req.user.userId;

        const [result] = await db.query(
            'INSERT INTO FAVORITE_STYLE (user_id, result_id) VALUES (?, ?)',
            [userId, resultId]
        );

        res.status(201).json({
            success: true,
            message: 'Added to favorites successfully',
            data: {
                favoriteId: result.insertId
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to add to favorites',
            error: error.message
        });
    }
};

// Submit feedback
exports.submitFeedback = async (req, res) => {
    try {
        const { resultId, rating, comment } = req.body;
        const userId = req.user.userId;

        const [result] = await db.query(
            'INSERT INTO FEEDBACK (user_id, result_id, rating, comment) VALUES (?, ?, ?, ?)',
            [userId, resultId, rating, comment]
        );

        res.status(201).json({
            success: true,
            message: 'Feedback submitted successfully',
            data: {
                feedbackId: result.insertId
            }
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to submit feedback',
            error: error.message
        });
    }
};
```

### src/controllers/productController.js
```javascript
const db = require('../config/database');

// Get all products
exports.getAllProducts = async (req, res) => {
    try {
        const { category, minPrice, maxPrice, minSustainability } = req.query;

        let query = 'SELECT * FROM PRODUCT WHERE 1=1';
        const params = [];

        if (category) {
            query += ' AND category = ?';
            params.push(category);
        }

        if (minPrice) {
            query += ' AND price >= ?';
            params.push(minPrice);
        }

        if (maxPrice) {
            query += ' AND price <= ?';
            params.push(maxPrice);
        }

        if (minSustainability) {
            query += ' AND sustainability_rating >= ?';
            params.push(minSustainability);
        }

        query += ' ORDER BY sustainability_rating DESC';

        const [products] = await db.query(query, params);

        res.status(200).json({
            success: true,
            count: products.length,
            data: products
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to get products',
            error: error.message
        });
    }
};

// Get product by ID
exports.getProductById = async (req, res) => {
    try {
        const { id } = req.params;

        const [products] = await db.query(
            'SELECT * FROM PRODUCT WHERE product_id = ?',
            [id]
        );

        if (products.length === 0) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        res.status(200).json({
            success: true,
            data: products[0]
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: 'Failed to get product',
            error: error.message
        });
    }
};
```

## 🛣️ Routes

### src/routes/authRoutes.js
```javascript
const express = require('express');
const router = express.Router();
const authController = require('../controllers/authController');
const authMiddleware = require('../middleware/auth');

router.post('/register', authController.register);
router.post('/login', authController.login);
router.get('/me', authMiddleware, authController.getCurrentUser);

module.exports = router;
```

### src/routes/styleRoutes.js
```javascript
const express = require('express');
const router = express.Router();
const styleController = require('../controllers/styleController');
const authMiddleware = require('../middleware/auth');

router.post('/profile', authMiddleware, styleController.createStyleProfile);
router.post('/generate', authMiddleware, styleController.generateStyleRecommendation);
router.get('/history', authMiddleware, styleController.getStyleHistory);
router.post('/favorites', authMiddleware, styleController.addToFavorites);
router.post('/feedback', authMiddleware, styleController.submitFeedback);

module.exports = router;
```

### src/routes/productRoutes.js
```javascript
const express = require('express');
const router = express.Router();
const productController = require('../controllers/productController');

router.get('/', productController.getAllProducts);
router.get('/:id', productController.getProductById);

module.exports = router;
```

## 🤖 AI Service

### src/services/aiService.js
```javascript
// AI Service for generating style recommendations

exports.generateRecommendation = async (input) => {
    const { bodyType, skinTone, occasion, budget, preferences } = input;

    // Personal Styling Logic
    const topWear = getTopWear(bodyType, occasion);
    const bottomWear = getBottomWear(bodyType, occasion);
    const accessories = getAccessories(occasion);
    const colorPalette = getColorPalette(skinTone, occasion);
    const sustainabilityScore = calculateSustainabilityScore(preferences, budget);

    return {
        topWear,
        bottomWear,
        accessories,
        colorPalette,
        sustainabilityScore,
        tips: generateStyleTips(bodyType, occasion)
    };
};

function getTopWear(bodyType, occasion) {
    const styles = {
        'Pear': occasion === 'Formal' ? 'Structured blazer' : 'Fitted top',
        'Apple': 'V-neck top',
        'Hourglass': 'Fitted blouse',
        'Rectangle': 'Peplum top'
    };
    return styles[bodyType] || 'Classic shirt';
}

function getBottomWear(bodyType, occasion) {
    const styles = {
        'Pear': 'Dark colored trousers',
        'Apple': 'Straight leg pants',
        'Hourglass': 'High-waisted skirt',
        'Rectangle': 'Wide leg pants'
    };
    return styles[bodyType] || 'Classic jeans';
}

function getAccessories(occasion) {
    const accessories = {
        'Formal': ['Watch', 'Minimal jewelry', 'Leather bag'],
        'Casual': ['Sunglasses', 'Canvas bag', 'Bracelet'],
        'Party': ['Statement earrings', 'Clutch', 'Bold necklace']
    };
    return accessories[occasion] || ['Simple accessories'];
}

function getColorPalette(skinTone, occasion) {
    const palettes = {
        'Fair_Formal': 'Neo Modern',
        'Fair_Casual': 'Ocean Breeze',
        'Medium_Formal': 'Earth Luxe',
        'Medium_Casual': 'Vibrant Street',
        'Dark_Formal': 'Earth Luxe',
        'Dark_Casual': 'Vibrant Street'
    };
    return palettes[`${skinTone}_${occasion}`] || 'Calm Minimalist';
}

function calculateSustainabilityScore(preferences, budget) {
    let score = 50;
    if (preferences.includes('sustainable')) score += 20;
    if (budget < 100) score += 10;
    if (preferences.includes('reuse')) score += 15;
    return Math.min(score, 100);
}

function generateStyleTips(bodyType, occasion) {
    return [
        `For ${bodyType} body type, focus on balanced proportions`,
        `${occasion} occasions call for appropriate formality`,
        'Consider sustainable fabric choices',
        'Reuse and remix existing wardrobe items'
    ];
}
```

## 🚀 Main Application

### src/app.js
```javascript
const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
require('dotenv').config();

const config = require('./config/config');
const errorHandler = require('./middleware/errorHandler');

// Import routes
const authRoutes = require('./routes/authRoutes');
const styleRoutes = require('./routes/styleRoutes');
const productRoutes = require('./routes/productRoutes');

const app = express();

// Middleware
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Routes
app.use(`/api/${config.apiVersion}/auth`, authRoutes);
app.use(`/api/${config.apiVersion}/style`, styleRoutes);
app.use(`/api/${config.apiVersion}/products`, productRoutes);

// Health check
app.get('/health', (req, res) => {
    res.status(200).json({
        success: true,
        message: 'Style Lens API is running',
        version: config.apiVersion
    });
});

// Error handler
app.use(errorHandler);

// Start server
const PORT = config.port;
app.listen(PORT, () => {
    console.log(`🚀 Server running on port ${PORT}`);
    console.log(`📡 API Version: ${config.apiVersion}`);
    console.log(`🌍 Environment: ${process.env.NODE_ENV}`);
});

module.exports = app;
```

## 📝 API Documentation

### Base URL
```
http://localhost:3000/api/v1
```

### Endpoints

#### Authentication
- `POST /auth/register` - Register new user
- `POST /auth/login` - Login user
- `GET /auth/me` - Get current user (requires auth)

#### Style
- `POST /style/profile` - Create style profile (requires auth)
- `POST /style/generate` - Generate recommendation (requires auth)
- `GET /style/history` - Get style history (requires auth)
- `POST /style/favorites` - Add to favorites (requires auth)
- `POST /style/feedback` - Submit feedback (requires auth)

#### Products
- `GET /products` - Get all products
- `GET /products/:id` - Get product by ID

---

**Backend API Version**: 1.0  
**Status**: ✅ READY FOR DEPLOYMENT
