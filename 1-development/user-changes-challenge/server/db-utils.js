const { Pool } = require('pg');

let DATABASE_URL=`postgres://localhost:5432/ixchallenge`

// Database connection
const pool = new Pool({
    connectionString: DATABASE_URL
});

module.exports = {
    query: (text, params) => pool.query(text, params),
};
