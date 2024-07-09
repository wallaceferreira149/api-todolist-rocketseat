package com.arcanus.todolist.task.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record CreateTaskDTO(
        String description,
        @NotBlank String title,
        String priority,
        LocalDateTime startDate,
        @Future(message = "A data não deve ser anterior a atual") LocalDateTime finishDate) {

}
