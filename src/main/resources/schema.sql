DROP TABLE IF EXISTS enrollment_seats;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS movie_descriptions;
DROP TABLE IF EXISTS seat_reservations;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS theaters;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS movies;

CREATE TABLE `movie_reservation_system`.`members`
(
    `seq`             BIGINT       NOT NULL AUTO_INCREMENT,
    `member_id`       VARCHAR(20)  NULL,
    `member_password` VARCHAR(100) NULL,
    `gender`          VARCHAR(5)   NULL,
    `birth`           DATE         NULL,
    `name`            VARCHAR(20)  NULL,
    `phone_number`    VARCHAR(45)  NULL,
    `create_at`       DATETIME     NULL,
    `modify_at`       DATETIME     NULL,
    `role_name`       VARCHAR(20)  NULL,
    `enable`          BOOLEAN      NULL,
    PRIMARY KEY (`seq`),
    UNIQUE INDEX `member_id_UNIQUE` (`member_id` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'moive_reservateion_system.Members';

CREATE TABLE `movie_reservation_system`.`movies`
(
    `seq`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `movie_name` VARCHAR(100) NOT NULL COMMENT 'Movie\'s name',
    `poster`     VARCHAR(200) NOT NULL COMMENT 'Poster\'s URI',
    PRIMARY KEY (`seq`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `movie_reservation_system`.`theaters`
(
    `seq`  BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`seq`)
)
    DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `movie_reservation_system`.`schedules`
(
    `seq`          BIGINT      NOT NULL AUTO_INCREMENT,
    `date`         DATE        NULL,
    `start_time`   VARCHAR(10) NOT NULL DEFAULT '09:00',
    `movies_seq`   BIGINT      NOT NULL,
    `theaters_seq` BIGINT      NOT NULL,
    PRIMARY KEY (`seq`),
    CONSTRAINT `movies_seq`
        FOREIGN KEY (`movies_seq`)
            REFERENCES `movie_reservation_system`.`movies` (`seq`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `theaters_seq`
        FOREIGN KEY (`theaters_seq`)
            REFERENCES `movie_reservation_system`.`theaters` (`seq`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'movie_reservation_system.schedules';

CREATE TABLE `movie_reservation_system`.`seats`
(
    `seq`           BIGINT      NOT NULL AUTO_INCREMENT,
    `schedules_seq` BIGINT      NOT NULL,
    `seat_row`      INT         NOT NULL,
    `seat_column`   INT         NOT NULL,
    `status`        VARCHAR(10) NOT NULL DEFAULT 'normal',
    PRIMARY KEY (`seq`),
    CONSTRAINT `schedules_seq`
        FOREIGN KEY (`schedules_seq`)
            REFERENCES `movie_reservation_system`.`schedules` (`seq`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'movie_reservation_system.seats';

CREATE TABLE `movie_reservation_system`.`categories`
(
    `seq`  BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`seq`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `movie_reservation_system`.`movie_descriptions`
(
    `seq`            BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    `movies_seq`     BIGINT      NOT NULL COMMENT 'Foreign Key',
    `categories_seq` BIGINT      NOT NULL COMMENT 'Foreign Key',
    `story`          TEXT        NOT NULL COMMENT 'Content',
    `running_time`   INT         NOT NULL COMMENT 'Running TIme',
    `director`       VARCHAR(30) NOT NULL COMMENT 'Director',
    `actor`          VARCHAR(45) NOT NULL COMMENT 'Actor list. ex){a, b, c, d}',
    `age_limit`      INT         NOT NULL COMMENT '0 12 15 19',
    PRIMARY KEY (`seq`),
    INDEX `movies_seq_idx` (`movies_seq` ASC) VISIBLE,
    INDEX `categories_seq_idx` (`categories_seq` ASC) VISIBLE,
    CONSTRAINT `md_movies_seq`
        FOREIGN KEY (`movies_seq`)
            REFERENCES `movie_reservation_system`.`movies` (`seq`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `categories_seq`
        FOREIGN KEY (`categories_seq`)
            REFERENCES `movie_reservation_system`.`categories` (`seq`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `movie_reservation_system`.`comments`
(
    `seq`         BIGINT   NOT NULL AUTO_INCREMENT,
    `members_seq` BIGINT   NOT NULL,
    `movies_seq`  BIGINT   NOT NULL,
    `content`     TEXT     NOT NULL,
    `create_at`   DATETIME NOT NULL,
    `modify_at`   DATETIME NOT NULL,
    PRIMARY KEY (`seq`),
    INDEX `members_seq_idx` (`members_seq` ASC) VISIBLE,
    INDEX `movies_seq_idx` (`movies_seq` ASC) VISIBLE,
    CONSTRAINT `members_seq`
        FOREIGN KEY (`members_seq`)
            REFERENCES `movie_reservation_system`.`members` (`seq`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `comment_movies_seq`
        FOREIGN KEY (`movies_seq`)
            REFERENCES `movie_reservation_system`.`movies` (`seq`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `movie_reservation_system`.`payments`
(
    `seq`            BIGINT      NOT NULL AUTO_INCREMENT,
    `movies_seq`     BIGINT      NOT NULL,
    `members_seq`    BIGINT      NOT NULL,
    `card_number`    VARCHAR(45) NOT NULL,
    `count_child`    INT         NOT NULL,
    `count_teenager` INT         NOT NULL,
    `count_adult`    INT         NOT NULL,
    `pay_amount`     INT         NOT NULL,
    `payment_at`     DATETIME    NOT NULL,
    PRIMARY KEY (`seq`),
    INDEX `movies_seq_idx` (`movies_seq` ASC) VISIBLE,
    INDEX `members_seq_idx` (`members_seq` ASC) VISIBLE,
    CONSTRAINT `pay_movies_seq`
        FOREIGN KEY (`movies_seq`)
            REFERENCES `movie_reservation_system`.`movies` (`seq`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `pay_members_seq`
        FOREIGN KEY (`members_seq`)
            REFERENCES `movie_reservation_system`.`members` (`seq`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `movie_reservation_system`.`enrollment_seats`
(
    `seq`          BIGINT NOT NULL AUTO_INCREMENT,
    `payments_seq` BIGINT NOT NULL,
    `seats_seq`    BIGINT NOT NULL,
    PRIMARY KEY (`seq`),
    INDEX `enrollment_payments_seq_idx` (`payments_seq` ASC) VISIBLE,
    INDEX `enrollment_seats_seq_idx` (`seats_seq` ASC) VISIBLE,
    CONSTRAINT `enrollment_payments_seq`
        FOREIGN KEY (`payments_seq`)
            REFERENCES `movie_reservation_system`.`payments` (`seq`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `enrollment_seats_seq`
        FOREIGN KEY (`seats_seq`)
            REFERENCES `movie_reservation_system`.`seats` (`seq`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;