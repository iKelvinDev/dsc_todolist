ALTER TABLE task
ADD CONSTRAINT fk_user_task FOREIGN KEY (id_user) REFERENCES user (id);