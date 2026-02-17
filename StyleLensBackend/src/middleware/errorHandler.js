const config = require('../config/config');

const errorHandler = (err, req, res, next) => {
    console.error('Error:', err.stack);

    const statusCode = err.statusCode || 500;
    const message = err.message || 'Internal Server Error';

    res.status(statusCode).json({
        success: false,
        message: message,
        error: config.nodeEnv === 'development' ? {
            stack: err.stack,
            details: err
        } : {}
    });
};

module.exports = errorHandler;
