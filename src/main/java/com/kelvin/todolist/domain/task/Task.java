package com.kelvin.todolist.domain.task;

import java.time.LocalDate;

import com.kelvin.todolist.domain.enumeration.TaskPriority;
import com.kelvin.todolist.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "task")
@Entity(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "Task title is required")
    private String title;

    private String description;

    private LocalDate startDate = LocalDate.now();

    //@NotBlank(message = "Task due date is required")
    private LocalDate dueDate;

    //@NotBlank(message = "Task priority is required")
    private TaskPriority priority;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
