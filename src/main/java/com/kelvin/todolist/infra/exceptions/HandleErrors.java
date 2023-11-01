package com.kelvin.todolist.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HandleErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleError404(EntityNotFoundException erro){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleErro400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorAttribute::new));
    }

    public record ErrorAttribute(String field, String message) {
        public ErrorAttribute(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
    
}