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
    type VARCHAR(20) NOT NULL,
    FOREIGN KEY (email) REFERENCES registration(email)
);
CREATE TABLE profile (
    userId INT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    age INT,
    gender VARCHAR(50),
    weight INT,
    height INT,
    FOREIGN KEY (userId) REFERENCES registration(id)
);


