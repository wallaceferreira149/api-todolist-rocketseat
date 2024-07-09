package com.arcanus.todolist.task.dto;

import java.time.Instant;
import java.time.LocalDateTime;

import com.arcanus.todolist.task.entity.Task;

public record TaskDTO(
    Long id,
    String description,
    String title,
    String priority,
    LocalDateTime startDate,
    LocalDateTime finishDate,
    Long userId,
    Instant createdAt) {

  public TaskDTO(Task entity) {
    this(
        entity.getId(),
        entity.getDescription(),
        entity.getTitle(),
        entity.getPriority(),
        entity.getStartDate(),
        entity.getFinishDate(),
        entity.getUserId(),
        entity.getCreatedAt());
  }
}