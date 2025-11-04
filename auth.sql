CREATE TABLE IF NOT EXISTS users(
    id serial PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    role VARCHAR(5) NOT NULL,
    user_full_name VARCHAR(100) NOT NULL
);


INSERT INTO users(username,password_hash,email,role,user_full_name) VALUES ('lbotta','EX-PWD-HASH','lohitkrishna39@hmail.com','ADMIN','Lohit Krishna Reddy Botta');


