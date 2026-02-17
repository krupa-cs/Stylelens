module.exports = {
    port: process.env.PORT || 3000,
    jwtSecret: process.env.JWT_SECRET,
    jwtExpire: process.env.JWT_EXPIRE || '7d',
    apiVersion: process.env.API_VERSION || 'v1',
    baseUrl: process.env.BASE_URL || 'http://localhost:3000',
    nodeEnv: process.env.NODE_ENV || 'development'
};
