CREATE TABLE task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    startDate DATE,
    dueDate DATE,
    priority VARCHAR(255) NOT NULL,
    completed BOOLEAN DEFAULT 0,
    user_id int
);