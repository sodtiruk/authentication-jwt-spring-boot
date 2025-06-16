
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key ของผู้ใช้',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT 'ชื่อผู้ใช้ที่ไม่ซ้ำกัน',
    email VARCHAR(255) NOT NULL UNIQUE COMMENT 'อีเมลของผู้ใช้',
    password VARCHAR(255) NOT NULL COMMENT 'รหัสผ่าน',
    first_name VARCHAR(100) COMMENT 'ชื่อจริง',
    last_name VARCHAR(100) COMMENT 'นามสกุล'
) COMMENT = 'ตารางเก็บข้อมูลผู้ใช้งาน';