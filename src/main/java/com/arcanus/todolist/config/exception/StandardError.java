package com.arcanus.todolist.config.exception;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StandardError {
  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}
