DROP TABLE IF EXISTS members, roles, question, answer, itinerary, itinerary_detail, hotplace, heart_hotplace CASCADE;

CREATE TABLE `members`
(
    `member_id`                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username`                   VARCHAR(40)  NOT NULL,
    `id`                         VARCHAR(40)  NOT NULL UNIQUE,
    `password`                   VARCHAR(255) NOT NULL,
    `nickname`                   VARCHAR(40)  NOT NULL UNIQUE,
    `email`                      VARCHAR(255) NOT NULL UNIQUE,
    `token`                      VARCHAR(1000) NULL,
    `password_token`             VARCHAR(1000) NULL,
    `password_token_expiry_date` DATETIME NULL,
    `created_date`               DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date`         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `roles`
(
    `role_id`   BIGINT AUTO_INCREMENT PRIMARY KEY,
    `member_id` BIGINT      NOT NULL,
    `role_name` VARCHAR(20) NOT NULL DEFAULT 'USER' CHECK (role_name IN ('ADMIN', 'USER')),
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE
);

CREATE TABLE `hotplace`
(
    `hotplace_id`        BIGINT AUTO_INCREMENT PRIMARY KEY,
    `member_id`          BIGINT       NOT NULL,
    `title`              VARCHAR(100) NOT NULL,
    `content`            VARCHAR(255) NOT NULL,
    `address`            VARCHAR(200) NOT NULL,
    `heart`              INT         DEFAULT 0,
    `save_folder`        VARCHAR(45) DEFAULT NULL,
    `original_file`      VARCHAR(50) DEFAULT NULL,
    `save_file`          VARCHAR(50) DEFAULT NULL,
    `created_date`       DATETIME    DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE,
    INDEX                `idx_hotplace_member_id` (`member_id`)
);

CREATE TABLE `heart_hotplace`
(
    `heart_hotplace_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `hotplace_id`       BIGINT NOT NULL,
    `member_id`         BIGINT NOT NULL,
    FOREIGN KEY (`hotplace_id`) REFERENCES `hotplace` (`hotplace_id`) ON DELETE CASCADE,
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE,
    INDEX               `idx_heart_hotplace_hotplace_id` (`hotplace_id`),
    INDEX               `idx_heart_hotplace_member_id` (`member_id`),
    UNIQUE KEY `uq_heart_hotplace_hotplace_member` (`hotplace_id`, `member_id`)

);

CREATE TABLE `itinerary`
(
    `itinerary_id`       BIGINT AUTO_INCREMENT PRIMARY KEY,
    `start_date`         DATE         NOT NULL,
    `end_date`           DATE         NOT NULL,
    `title`              VARCHAR(100) NOT NULL,
    `content`            TEXT         NOT NULL,
    `member_id`          BIGINT       NOT NULL,
    `created_date`       DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE
);

CREATE TABLE `itinerary_detail`
(
    `itinerary_detail_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `itinerary_order`     INT    NOT NULL,
    `itinerary_id`        BIGINT NOT NULL,
    `content_id`          INT    NOT NULL,
    FOREIGN KEY (`itinerary_id`) REFERENCES `itinerary` (`itinerary_id`) ON DELETE CASCADE
);

CREATE TABLE `question`
(
    `question_id`        BIGINT AUTO_INCREMENT PRIMARY KEY,
    `title`              VARCHAR(100) NOT NULL,
    `content`            VARCHAR(255) NOT NULL,
    `hit`                INT                   DEFAULT 0,
    `has_response`       BOOLEAN      NOT NULL DEFAULT false,
    `member_id`          BIGINT       NOT NULL,
    `created_date`       DATETIME              DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date` DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE
);

CREATE TABLE `answer`
(
    `answer_id`          BIGINT AUTO_INCREMENT PRIMARY KEY,
    `question_id`        BIGINT       NOT NULL,
    `member_id`          BIGINT       NOT NULL,
    `content`            VARCHAR(255) NOT NULL,
    `created_date`       DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE,
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE
);