DROP TABLE IF EXISTS members, roles, question, answer, itinerary, itinerary_detail, hotplace, comment CASCADE;

CREATE TABLE `members`
(
    `member_id`          BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username`           VARCHAR(40) NOT NULL,
    `id`                 VARCHAR(40) NOT NULL UNIQUE,
    `password`           VARCHAR(255) NOT NULL,
    `nickname`           VARCHAR(40) NOT NULL UNIQUE,
    `email`              VARCHAR(255) NOT NULL,
    `token` VARCHAR(1000) NULL DEFAULT NULL,
    `created_date`       DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `roles`
(
    `role_id`            BIGINT AUTO_INCREMENT PRIMARY KEY,
    `member_id`          BIGINT      NOT NULL,
    `role_name`          VARCHAR(20) NOT NULL DEFAULT 'USER' CHECK (role_name IN ('ADMIN', 'USER')),
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE
);

CREATE TABLE `hotplace`
(
    `hotplace_id`        BIGINT AUTO_INCREMENT PRIMARY KEY,
    `content`            VARCHAR(255)   NOT NULL,
    `latitude`           DECIMAL(10, 8) NOT NULL,
    `longitude`          DECIMAL(11, 8) NOT NULL,
    `hotplace_img`       VARCHAR(255)   NOT NULL,
    `hit`                INT            NOT NULL,
    `heart`              INT            NOT NULL,
    `member_id`          BIGINT         NOT NULL,
    `created_date`       DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE
);

CREATE TABLE `comment`
(
    `comment_id`         BIGINT AUTO_INCREMENT PRIMARY KEY,
    `member_id`          BIGINT       NOT NULL,
    `hotplace_id`        BIGINT       NOT NULL,
    `content`            VARCHAR(255) NOT NULL,
    `created_date`       DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE,
    FOREIGN KEY (`hotplace_id`) REFERENCES `hotplace` (`hotplace_id`) ON DELETE CASCADE
);

CREATE TABLE `itinerary`
(
    `itinerary_id`       BIGINT AUTO_INCREMENT PRIMARY KEY,
    `start_date`         DATE        NOT NULL,
    `end_date`           DATE        NOT NULL,
    `title`              VARCHAR(40) NOT NULL,
    `content`            TEXT        NOT NULL,
    `member_id`          BIGINT      NOT NULL,
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
    `title`              VARCHAR(40)  NOT NULL,
    `content`            VARCHAR(255) NOT NULL,
    `hit`                INT                   DEFAULT 0,
    `has_response`       BOOLEAN      NOT NULL DEFAULT false,
    `member_id`          BIGINT       NOT NULL,
    `created_date`       DATETIME DEFAULT CURRENT_TIMESTAMP,
    `last_modified_date` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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