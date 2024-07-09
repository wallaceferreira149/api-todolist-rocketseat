package com.arcanus.todolist.config.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arcanus.todolist.filter.exceptions.UserUnauthorizedException;
import com.arcanus.todolist.task.exceptions.TaskNotFoundException;
import com.arcanus.todolist.user.exceptions.UserAlreadyExistsException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<StandardError> userAlreadyExists(
      UserAlreadyExistsException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.CONFLICT.value());
    err.setError("Usuário já existente.");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  @ExceptionHandler(UserUnauthorizedException.class)
  public ResponseEntity<StandardError> userUnauthorized(
      UserUnauthorizedException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.UNAUTHORIZED.value());
    err.setError("Usuário não autorizado.");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<StandardError> taskNotFound(
      TaskNotFoundException e, HttpServletRequest request) {
    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.NOT_FOUND.value());
    err.setError("Não encontrado.");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

}
