use linkdin;
INSERT INTO user (user_id, email, password, phone, role, created_at) VALUES
(1, 'john@example.com', 'pass123', '9876543210', 'user', NOW()),
(2, 'alice@example.com', 'pass456', '9123456780', 'user', NOW()),
(3, 'hr@techcorp.com', 'pass789', '9000000000', 'admin', NOW());

INSERT INTO profile (profile_id, user_id, full_name, headline, summary, location, profile_photo) VALUES
(1, 1, 'John Doe', 'Full Stack Developer', 'Passionate developer', 'Mumbai', 'john.jpg'),
(2, 2, 'Alice Smith', 'Data Analyst', 'Data enthusiast', 'Delhi', 'alice.jpg');

INSERT INTO skill (skill_id, skill_name) VALUES
(1, 'Java'),
(2, 'SQL'),
(3, 'Python'),
(4, 'React');

INSERT INTO user_skill (user_id, skill_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);

INSERT INTO company (company_id, name, industry, location) VALUES
(1, 'TechCorp', 'Software', 'Bangalore'),
(2, 'DataWorks', 'Analytics', 'Hyderabad');

INSERT INTO job (job_id, company_id, title, description, location, salary, posted_date) VALUES
(1, 1, 'Backend Developer', 'Work on APIs', 'Bangalore', '10 LPA', NOW()),
(2, 2, 'Data Analyst', 'Analyze datasets', 'Hyderabad', '8 LPA', NOW());

INSERT INTO job_application (application_id, job_id, user_id, resume_url, status, applied_date) VALUES
(1, 1, 1, 'john_resume.pdf', 'Pending', NOW()),
(2, 2, 2, 'alice_resume.pdf', 'Shortlisted', NOW());

INSERT INTO post (post_id, user_id, content, media_url, created_at) VALUES
(1, 1, 'Excited to start a new project!', NULL, NOW()),
(2, 2, 'Learning advanced SQL queries!', NULL, NOW());

INSERT INTO reaction (reaction_id, post_id, user_id, type) VALUES
(1, 1, 2, 'Like'),
(2, 2, 1, 'Like');

 INSERT INTO message (message_id, sender_id, receiver_id, content, sent_time) VALUES
 (1, 1, 2, 'Hey Alice, how are you?', NOW()),
 (2, 2, 1, 'I am good! What about you?', NOW());

INSERT INTO connection (connection_id, user_id, connected_user_id, status, connected_date) VALUES
 (1, 1, 2, 'Accepted', NOW());

INSERT INTO education (education_id, user_id, institute, degree, start_year, end_year) VALUES
(1, 1, 'Mumbai University', 'B.Tech', 2018, 2022),
(2, 2, 'Delhi University', 'B.Sc Data Science', 2017, 2021);
