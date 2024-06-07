show databases;

use company;

show tables;

CREATE TABLE fileboard (
    no INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    filename VARCHAR(255),
    resdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);