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
        version: config.apiVersion,
        timestamp: new Date().toISOString()
    });
});

// Root endpoint
app.get('/', (req, res) => {
    res.status(200).json({
        success: true,
        message: 'Welcome to Style Lens API',
        version: config.apiVersion,
        endpoints: {
            health: '/health',
            auth: `/api/${config.apiVersion}/auth`,
            style: `/api/${config.apiVersion}/style`,
            products: `/api/${config.apiVersion}/products`
        }
    });
});

// 404 handler
app.use((req, res) => {
    res.status(404).json({
        success: false,
        message: 'Endpoint not found'
    });
});

// Error handler
app.use(errorHandler);

// Start server
const PORT = config.port;
app.listen(PORT, () => {
    console.log(`🚀 Style Lens API Server Started`);
    console.log(`📡 Port: ${PORT}`);
    console.log(`🌍 Environment: ${process.env.NODE_ENV}`);
    console.log(`📋 API Version: ${config.apiVersion}`);
    console.log(`🔗 Base URL: ${config.baseUrl}`);
    console.log(`✅ Server is ready to accept connections`);
});

module.exports = app;
