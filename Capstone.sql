CREATE DATABASE capstone;
USE capstone;

CREATE TABLE registration (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(40) NOT NULL UNIQUE,
    password VARCHAR(40) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    isVerified BOOLEAN DEFAULT FALSE
);

CREATE TABLE verification_tokens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    token VARCHAR(4) NOT NULL,
    FOREIGN KEY (email) REFERENCES registration(email)
);
