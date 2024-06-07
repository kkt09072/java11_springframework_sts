CREATE sequence file_seq increment by 1;

CREATE TABLE fileboard (
    no INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content VARCHAR2(1000) NOT NULL,
    filename VARCHAR(255),
    resdate TIMESTAMP DEFAULT sysdate
);