use linkdin;

SELECT 
    u.email,
    p.full_name,
    s.skill_name,
    j.title,
    c.name AS company,
    ja.status
FROM user u
LEFT JOIN profile p ON u.user_id = p.user_id
LEFT JOIN user_skill us ON u.user_id = us.user_id
LEFT JOIN skill s ON us.skill_id = s.skill_id
LEFT JOIN job_application ja ON u.user_id = ja.user_id
LEFT JOIN job j ON ja.job_id = j.job_id
LEFT JOIN company c ON j.company_id = c.company_id;


