CREATE DATABASE linkdin;
USE linkdin;

CREATE TABLE `user` (
  `user_id` INT PRIMARY KEY AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(20),
  `role` VARCHAR(50),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `profile` (
  `profile_id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `full_name` VARCHAR(100),
  `headline` VARCHAR(150),
  `summary` TEXT,
  `location` VARCHAR(100),
  `profile_photo` VARCHAR(255)
);

CREATE TABLE `connection` (
  `connection_id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `connected_user_id` INT NOT NULL,
  `status` VARCHAR(20),
  `connected_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `post` (
  `post_id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `content` TEXT,
  `media_url` VARCHAR(255),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `reaction` (
  `reaction_id` INT PRIMARY KEY AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `type` VARCHAR(20)
);

CREATE TABLE `message` (
  `message_id` INT PRIMARY KEY AUTO_INCREMENT,
  `sender_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  `content` TEXT,
  `sent_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `company` (
  `company_id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `industry` VARCHAR(50),
  `location` VARCHAR(100)
);

CREATE TABLE `job` (
  `job_id` INT PRIMARY KEY AUTO_INCREMENT,
  `company_id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` TEXT,
  `location` VARCHAR(100),
  `salary` DECIMAL(12, 2),
  `posted_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `job_application` (
  `application_id` INT PRIMARY KEY AUTO_INCREMENT,
  `job_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `resume_url` VARCHAR(255),
  `status` VARCHAR(20),
  `applied_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `skill` (
  `skill_id` INT PRIMARY KEY AUTO_INCREMENT,
  `skill_name` VARCHAR(50) NOT NULL
);

CREATE TABLE `education` (
  `education_id` INT PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `institute` VARCHAR(100),
  `degree` VARCHAR(50),
  `start_year` INT,
  `end_year` INT
);

CREATE TABLE `user_skill` (
  `user_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `skill_id`)
);

-- profile → user
ALTER TABLE profile
ADD CONSTRAINT fk_profile_user_user_id
FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- post → user
ALTER TABLE post
ADD CONSTRAINT fk_post_user_user_id
FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- reaction → post
ALTER TABLE reaction
ADD CONSTRAINT fk_reaction_post_post_id
FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE;

-- reaction → user
ALTER TABLE reaction
ADD CONSTRAINT fk_reaction_user_user_id
FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- connection → user (sender)
ALTER TABLE connection
ADD CONSTRAINT fk_connection_user_sender_id
FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- connection → user (receiver)
ALTER TABLE connection
ADD CONSTRAINT fk_connection_user_connected_user_id
FOREIGN KEY (connected_user_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- message → user (sender)
ALTER TABLE message
ADD CONSTRAINT fk_message_user_sender_id
FOREIGN KEY (sender_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- message → user (receiver)
ALTER TABLE message
ADD CONSTRAINT fk_message_user_receiver_id
FOREIGN KEY (receiver_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- job → company
ALTER TABLE job
ADD CONSTRAINT fk_job_company_company_id
FOREIGN KEY (company_id) REFERENCES company(company_id) ON DELETE CASCADE;

-- job_application → job
ALTER TABLE job_application
ADD CONSTRAINT fk_job_application_job_job_id
FOREIGN KEY (job_id) REFERENCES job(job_id) ON DELETE CASCADE;

-- job_application → user
ALTER TABLE job_application
ADD CONSTRAINT fk_job_application_user_user_id
FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- user_skill → user
ALTER TABLE user_skill
ADD CONSTRAINT fk_user_skill_user_user_id
FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE;

-- user_skill → skill
ALTER TABLE user_skill
ADD CONSTRAINT fk_user_skill_skill_skill_id
FOREIGN KEY (skill_id) REFERENCES skill(skill_id) ON DELETE CASCADE;

-- education → user
ALTER TABLE education
ADD CONSTRAINT fk_education_user_user_id
FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE;

SHOW TABLES;