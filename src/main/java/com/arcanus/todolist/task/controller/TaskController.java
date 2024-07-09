package com.arcanus.todolist.task.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arcanus.todolist.task.dto.CreateTaskDTO;
import com.arcanus.todolist.task.dto.TaskDTO;
import com.arcanus.todolist.task.entity.Task;
import com.arcanus.todolist.task.useCases.CreateTaskUseCase;
import com.arcanus.todolist.task.useCases.ListTasksByUserUseCase;
import com.arcanus.todolist.task.useCases.UpdateTaskByUserUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private CreateTaskUseCase createTaskUseCase;

  @Autowired
  private ListTasksByUserUseCase listTasksByUserUseCase;

  @Autowired
  private UpdateTaskByUserUseCase updateTaskByUserUseCase;

  @PostMapping
  public ResponseEntity<TaskDTO> create(
      @Valid @RequestBody CreateTaskDTO dto,
      HttpServletRequest request) {
    Long userId = (Long) request.getAttribute("userId");
    TaskDTO createdTask = createTaskUseCase.execute(dto, userId);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(createdTask.id()).toUri();
    System.out.println("Controller!!!");
    return ResponseEntity.created(uri).body(createdTask);
  }

  @GetMapping
  public ResponseEntity<List<Task>> findAllByUserId(HttpServletRequest request) {
    Long userId = (Long) request.getAttribute("userId");
    List<Task> tasks = listTasksByUserUseCase.execute(userId);
    return ResponseEntity.ok().body(tasks);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<List<Task>> update(
      @PathVariable Long id,
      @RequestBody TaskDTO dto,
      HttpServletRequest request) {
    Long userId = (Long) request.getAttribute("userId");
    List<Task> tasks = updateTaskByUserUseCase.execute(dto, userId, id);

    return ResponseEntity.ok().body(tasks);
  }

}
