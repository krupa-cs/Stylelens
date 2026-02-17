-- ============================================================================
-- STYLE LENS DATABASE SCHEMA
-- Database Management System: MySQL
-- Version: 1.0
-- Created: February 2, 2026
-- ============================================================================

-- Create Database
CREATE DATABASE IF NOT EXISTS style_lens_db;
USE style_lens_db;

-- ============================================================================
-- TABLE 1: USER
-- Purpose: Stores user account information
-- ============================================================================
CREATE TABLE USER (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    sustainability_preference BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_user_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 2: STYLE_PROFILE
-- Purpose: Stores user's physical and style characteristics
-- ============================================================================
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
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE,
    INDEX idx_profile_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 3: COLOR_PALETTE
-- Purpose: Stores predefined color schemes
-- ============================================================================
CREATE TABLE COLOR_PALETTE (
    palette_id INT PRIMARY KEY AUTO_INCREMENT,
    palette_name VARCHAR(100) NOT NULL,
    primary_color VARCHAR(7),
    secondary_color VARCHAR(7),
    accent_color VARCHAR(7),
    usage_type VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 4: STYLE_RESULT
-- Purpose: Stores AI-generated styling recommendations
-- ============================================================================
CREATE TABLE STYLE_RESULT (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    profile_id INT NOT NULL,
    palette_id INT,
    style_type ENUM('people', 'space', 'character', 'product', 'social_media') NOT NULL,
    recommendation_text TEXT,
    sustainability_score INT DEFAULT 0 CHECK (sustainability_score BETWEEN 0 AND 100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (profile_id) REFERENCES STYLE_PROFILE(profile_id) ON DELETE CASCADE,
    FOREIGN KEY (palette_id) REFERENCES COLOR_PALETTE(palette_id) ON DELETE SET NULL,
    INDEX idx_result_profile (profile_id),
    INDEX idx_result_palette (palette_id),
    INDEX idx_result_type (style_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 5: PRODUCT
-- Purpose: Stores sustainable product catalog
-- ============================================================================
CREATE TABLE PRODUCT (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(100),
    name VARCHAR(200),
    color VARCHAR(50),
    price DECIMAL(10,2),
    sustainability_rating INT DEFAULT 0 CHECK (sustainability_rating BETWEEN 0 AND 100),
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 6: FAVORITE_STYLE
-- Purpose: Stores user's saved/favorite styles
-- ============================================================================
CREATE TABLE FAVORITE_STYLE (
    favorite_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    result_id INT NOT NULL,
    saved_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE,
    FOREIGN KEY (result_id) REFERENCES STYLE_RESULT(result_id) ON DELETE CASCADE,
    INDEX idx_favorite_user (user_id),
    INDEX idx_favorite_result (result_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 7: FEEDBACK
-- Purpose: Stores user ratings and comments on style results
-- ============================================================================
CREATE TABLE FEEDBACK (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    result_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE,
    FOREIGN KEY (result_id) REFERENCES STYLE_RESULT(result_id) ON DELETE CASCADE,
    INDEX idx_feedback_user (user_id),
    INDEX idx_feedback_result (result_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 8: SPACE_STYLE
-- Purpose: Stores room/space-specific styling parameters
-- ============================================================================
CREATE TABLE SPACE_STYLE (
    space_id INT PRIMARY KEY AUTO_INCREMENT,
    room_type VARCHAR(100),
    size VARCHAR(50),
    lighting VARCHAR(50),
    mood VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 9: AI_MODEL
-- Purpose: Stores AI/ML model metadata
-- ============================================================================
CREATE TABLE AI_MODEL (
    model_id INT PRIMARY KEY AUTO_INCREMENT,
    model_type VARCHAR(100) NOT NULL,
    accuracy DECIMAL(5,2),
    version VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 10: AI_PREDICTION
-- Purpose: Stores AI model predictions and inputs
-- ============================================================================
CREATE TABLE AI_PREDICTION (
    prediction_id INT PRIMARY KEY AUTO_INCREMENT,
    model_id INT NOT NULL,
    input_features TEXT,
    output_result TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (model_id) REFERENCES AI_MODEL(model_id) ON DELETE CASCADE,
    INDEX idx_prediction_model (model_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- TABLE 11: SUSTAINABILITY_LOG
-- Purpose: Tracks user's sustainability actions and score changes
-- ============================================================================
CREATE TABLE SUSTAINABILITY_LOG (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    action_type VARCHAR(100),
    score_change INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE,
    INDEX idx_log_user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================================================
-- SAMPLE DATA INSERTION
-- ============================================================================

-- Insert Sample Users
INSERT INTO USER (name, email, password, sustainability_preference) VALUES
('Krupa Patel', 'krupa@example.com', 'hashed_password_1', TRUE),
('Rahul Sharma', 'rahul@example.com', 'hashed_password_2', TRUE),
('Priya Singh', 'priya@example.com', 'hashed_password_3', FALSE);

-- Insert Sample Color Palettes
INSERT INTO COLOR_PALETTE (palette_name, primary_color, secondary_color, accent_color, usage_type) VALUES
('Earth Luxe', '#8B7355', '#D4A574', '#E8DCC4', 'Formal'),
('Neo Modern', '#2C3E50', '#ECF0F1', '#3498DB', 'Casual'),
('Vibrant Street', '#E74C3C', '#F39C12', '#9B59B6', 'Bold'),
('Calm Minimalist', '#95A5A6', '#BDC3C7', '#FFFFFF', 'Minimal'),
('Ocean Breeze', '#1ABC9C', '#16A085', '#D5F4E6', 'Fresh');

-- Insert Sample Products
INSERT INTO PRODUCT (category, name, color, price, sustainability_rating, image_url) VALUES
('Clothing', 'Organic Cotton T-Shirt', 'White', 29.99, 85, 'products/tshirt1.jpg'),
('Clothing', 'Recycled Denim Jeans', 'Blue', 59.99, 90, 'products/jeans1.jpg'),
('Furniture', 'Bamboo Coffee Table', 'Natural', 199.99, 95, 'products/table1.jpg'),
('Accessories', 'Eco-Leather Bag', 'Brown', 79.99, 80, 'products/bag1.jpg'),
('Furniture', 'Reclaimed Wood Shelf', 'Oak', 149.99, 92, 'products/shelf1.jpg');

-- Insert Sample AI Models
INSERT INTO AI_MODEL (model_type, accuracy, version) VALUES
('Color Harmony Classifier', 87.50, 'v1.2'),
('Sustainability Predictor', 92.30, 'v2.0'),
('Body Type Matcher', 89.10, 'v1.5'),
('Mood-Based Styler', 85.60, 'v1.0');

-- Insert Sample Style Profile
INSERT INTO STYLE_PROFILE (user_id, body_type, face_shape, skin_tone, hair_type, height, preferences) VALUES
(1, 'Hourglass', 'Oval', 'Medium', 'Wavy', 165.00, 'Prefers bold colors, sustainable fashion'),
(2, 'Rectangle', 'Square', 'Fair', 'Straight', 178.00, 'Minimalist style, neutral tones'),
(3, 'Pear', 'Round', 'Dark', 'Curly', 160.00, 'Vibrant colors, traditional patterns');

-- Insert Sample Style Results
INSERT INTO STYLE_RESULT (profile_id, palette_id, style_type, recommendation_text, sustainability_score) VALUES
(1, 3, 'people', 'Bold outfit with vibrant street palette. Pair recycled denim with organic cotton top.', 88),
(2, 4, 'space', 'Minimalist living room with calm palette. Use bamboo furniture and neutral tones.', 92),
(3, 1, 'people', 'Traditional look with earth luxe palette. Eco-leather accessories recommended.', 85);

-- Insert Sample Favorites
INSERT INTO FAVORITE_STYLE (user_id, result_id) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Insert Sample Feedback
INSERT INTO FEEDBACK (user_id, result_id, rating, comment) VALUES
(1, 1, 5, 'Absolutely loved the recommendations! Very sustainable options.'),
(2, 2, 4, 'Great minimalist design. Would love more furniture options.'),
(3, 3, 5, 'Perfect traditional styling with modern sustainability twist!');

-- Insert Sample Sustainability Logs
INSERT INTO SUSTAINABILITY_LOG (user_id, action_type, score_change) VALUES
(1, 'Reused outfit', 5),
(1, 'Chose eco-product', 10),
(2, 'Shared style', 3),
(3, 'Rated sustainable product', 2);

-- ============================================================================
-- USEFUL QUERIES FOR ANDROID APP
-- ============================================================================

-- Query 1: Get user profile with latest style results
-- SELECT u.name, sp.body_type, sr.recommendation_text, sr.sustainability_score
-- FROM USER u
-- JOIN STYLE_PROFILE sp ON u.user_id = sp.user_id
-- JOIN STYLE_RESULT sr ON sp.profile_id = sr.profile_id
-- ORDER BY sr.created_at DESC;

-- Query 2: Get user's sustainability score
-- SELECT u.name, SUM(sl.score_change) as total_sustainability_score
-- FROM USER u
-- JOIN SUSTAINABILITY_LOG sl ON u.user_id = sl.user_id
-- GROUP BY u.user_id;

-- Query 3: Get trending color palettes (most used)
-- SELECT cp.palette_name, COUNT(sr.result_id) as usage_count
-- FROM COLOR_PALETTE cp
-- JOIN STYLE_RESULT sr ON cp.palette_id = sr.palette_id
-- GROUP BY cp.palette_id
-- ORDER BY usage_count DESC;

-- Query 4: Get top-rated style results
-- SELECT sr.recommendation_text, AVG(f.rating) as avg_rating
-- FROM STYLE_RESULT sr
-- JOIN FEEDBACK f ON sr.result_id = f.result_id
-- GROUP BY sr.result_id
-- HAVING avg_rating >= 4
-- ORDER BY avg_rating DESC;

-- Query 5: Get sustainable products
-- SELECT name, category, price, sustainability_rating
-- FROM PRODUCT
-- WHERE sustainability_rating >= 80
-- ORDER BY sustainability_rating DESC;

-- ============================================================================
-- DATABASE MAINTENANCE
-- ============================================================================

-- Create backup procedure (example)
-- DELIMITER //
-- CREATE PROCEDURE backup_user_data()
-- BEGIN
--     -- Backup logic here
-- END //
-- DELIMITER ;

-- ============================================================================
-- END OF SCHEMA
-- ============================================================================
