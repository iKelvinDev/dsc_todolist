ALTER TABLE task
ADD CONSTRAINT fk_user_task FOREIGN KEY (user_id) REFERENCES user (id);