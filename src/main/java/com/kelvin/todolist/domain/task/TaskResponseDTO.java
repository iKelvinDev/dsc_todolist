package com.kelvin.todolist.domain.task;

import java.time.LocalDate;

import com.kelvin.todolist.domain.enumeration.TaskPriority;

public record TaskResponseDTO(Long id, 
                              String title,
                              LocalDate dueDate,
                              TaskPriority priority) {
    
    public TaskResponseDTO(Task task) {
        this(task.getId(), task.getTitle(), task.getDueDate(), task.getPriority());
    }
}
