package com.kelvin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelvin.todolist.domain.task.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
