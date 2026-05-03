
use linkdin;
DELIMITER //

CREATE PROCEDURE InsertDummyUsersAndProfiles(IN num_records INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE last_user_id INT;
    
    WHILE i <= num_records DO
        INSERT INTO user (email, password, phone, role) 
        VALUES (CONCAT('user', i, '@example.com'), 'pass123', CONCAT('123456789', i), 'user');
        
        SET last_user_id = LAST_INSERT_ID();
        
        INSERT INTO profile (user_id, full_name, headline, location) 
        VALUES (last_user_id, CONCAT('Test User ', i), 'Software Engineer', 'Remote');
        
        SET i = i + 1;
    END WHILE;
END //

CREATE PROCEDURE InsertDummyCompaniesAndJobs(IN num_records INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE last_comp_id INT;
    
    WHILE i <= num_records DO
        INSERT INTO company (name, industry, location) 
        VALUES (CONCAT('Tech Company ', i), 'IT', 'City Center');
        
        SET last_comp_id = LAST_INSERT_ID();
        
        INSERT INTO job (company_id, title, description, location, salary) 
        VALUES (last_comp_id, CONCAT('Developer Level ', i), 'Join our team', 'City Center', 80000.00 + (i * 5000));
        
        SET i = i + 1;
    END WHILE;
END //

CREATE PROCEDURE InsertDummySkills(IN num_records INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    
    WHILE i <= num_records DO
        INSERT INTO skill (skill_name) 
        VALUES (CONCAT('Core Skill ', i));
        
        SET i = i + 1;
    END WHILE;
END //

CREATE PROCEDURE ApplyForJob(IN p_user_id INT, IN p_job_id INT, IN p_resume_url VARCHAR(255))
BEGIN
    INSERT INTO job_application (job_id, user_id, resume_url, status)
    VALUES (p_job_id, p_user_id, p_resume_url, 'Pending');
END //

CREATE PROCEDURE ConnectUsers(IN p_sender_id INT, IN p_receiver_id INT)
BEGIN
    INSERT INTO connection (user_id, connected_user_id, status)
    VALUES (p_sender_id, p_receiver_id, 'Accepted');
END //

CREATE PROCEDURE CreatePost(IN p_user_id INT, IN p_content TEXT)
BEGIN
    INSERT INTO post (user_id, content)
    VALUES (p_user_id, p_content);
END //

CREATE PROCEDURE GetUserFullProfile(IN p_user_id INT)
BEGIN
    SELECT u.user_id, u.email, p.full_name, p.headline, p.location
    FROM user u
    JOIN profile p ON u.user_id = p.user_id
    WHERE u.user_id = p_user_id;
END //

DELIMITER //


CREATE PROCEDURE InsertDummyEducation(IN num_records INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    
    WHILE i <= num_records DO
        INSERT INTO education (user_id, institute, degree, start_year, end_year) 
        VALUES (i, CONCAT('University ', i), 'Bachelor of Science', 2014 + i, 2018 + i);
        
        SET i = i + 1;
    END WHILE;
END //

CREATE PROCEDURE InsertDummyUserSkills(IN num_records INT)
BEGIN
    DECLARE i INT DEFAULT 1;
    
    WHILE i <= num_records DO
        INSERT INTO user_skill (user_id, skill_id) 
        VALUES (i, i);
        
        SET i = i + 1;
    END WHILE;
END //

DELIMITER ;


CALL InsertDummyUsersAndProfiles(5);
CALL InsertDummyCompaniesAndJobs(5);
CALL InsertDummySkills(5);

CALL ApplyForJob(1, 1, 'http://link.to/resume1.pdf');
CALL ApplyForJob(2, 3, 'http://link.to/resume2.pdf');

CALL ConnectUsers(1, 2);
CALL ConnectUsers(2, 3);
CALL ConnectUsers(4, 5);

CALL CreatePost(1, 'Hello world! This is my first post.');
CALL CreatePost(2, 'Looking for new opportunities.');

CALL GetUserFullProfile(1);
CALL InsertDummyEducation(5);
CALL InsertDummyUserSkills(5);