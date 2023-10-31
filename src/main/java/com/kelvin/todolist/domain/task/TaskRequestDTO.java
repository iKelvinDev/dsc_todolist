package com.kelvin.todolist.domain.task;

import java.time.LocalDate;

import com.kelvin.todolist.domain.enumeration.TaskPriority;

public record TaskRequestDTO(String title, String description, LocalDate dueDate, TaskPriority priority) {

}
