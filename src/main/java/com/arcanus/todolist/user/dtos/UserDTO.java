package com.arcanus.todolist.user.dtos;

import java.time.Instant;

import com.arcanus.todolist.user.entity.User;

public record UserDTO(
    Long id,
    String username,
    String name,
    String password,
    Instant createdAt) {

  public UserDTO(User entity) {
    this(
        entity.getId(),
        entity.getUsername(),
        entity.getName(),
        entity.getPassword(),
        entity.getCreatedAt());
  }
}
