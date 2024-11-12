
-- Tạo bảng payments với các cột và ràng buộc cần thiết
CREATE TABLE IF NOT EXISTS payments (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        user_id BIGINT NOT NULL,
                                        status INT NOT NULL,
                                        money FLOAT NOT NULL,
                                        posts BIGINT NOT NULL,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                        FOREIGN KEY (user_id) REFERENCES user(id)
    );
