DELIMITER //

-- adding a connection

CREATE PROCEDURE AddConnectionRequest(IN p_sender_id INT, IN p_target_user_id INT)
BEGIN
    -- Check if a connection or request already exists between these two users
    IF NOT EXISTS (
        SELECT 1 FROM connection 
        WHERE (user_id = p_sender_id AND connected_user_id = p_target_user_id)
           OR (user_id = p_target_user_id AND connected_user_id = p_sender_id)
    ) THEN
        -- Insert the new connection request
        INSERT INTO connection (user_id, connected_user_id, status)
        VALUES (p_sender_id, p_target_user_id, 'Pending');
    END IF;
END //


-- Removing a Connection

CREATE PROCEDURE RemoveConnection(IN p_user_id INT, IN p_connected_user_id INT)
BEGIN
    DELETE FROM connection
    WHERE (user_id = p_user_id AND connected_user_id = p_connected_user_id)
       OR (user_id = p_connected_user_id AND connected_user_id = p_user_id);
END //



-- Fetching Applicants for a Specific Job
CREATE PROCEDURE GetJobApplicants(IN p_job_id INT)
BEGIN
    SELECT 
        ja.application_id, 
        u.email, 
        pr.full_name, 
        ja.resume_url, 
        ja.status, 
        ja.applied_date
    FROM job_application ja
    JOIN user u ON ja.user_id = u.user_id
    JOIN profile pr ON u.user_id = pr.user_id
    WHERE ja.job_id = p_job_id;
END //

-- Updating Job Application Status
CREATE PROCEDURE UpdateJobApplicationStatus(IN p_application_id INT, IN p_new_status VARCHAR(20))
BEGIN
    UPDATE job_application
    SET status = p_new_status
    WHERE application_id = p_application_id;
END //

DELIMITER ;

CALL AddConnectionRequest(3, 5);

CALL UpdateJobApplicationStatus(1, 'Reviewed');

CALL GetJobApplicants(1);

CALL RemoveConnection(1, 2);