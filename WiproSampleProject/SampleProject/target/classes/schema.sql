CREATE TABLE IF NOT EXISTS users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role VARCHAR(20) NOT NULL,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  phone VARCHAR(15),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Optional: Insert a default admin account
-- (Make sure to replace the hash with a real BCrypt hash of your chosen password)
-- Example:
-- INSERT INTO users (username, password_hash, role, name, email, phone)
-- VALUES ('admin', '$2a$10$exampleexampleexampleexampleexamp', 'ADMIN', 'Super Admin', 'admin@example.com', '1234567890');
