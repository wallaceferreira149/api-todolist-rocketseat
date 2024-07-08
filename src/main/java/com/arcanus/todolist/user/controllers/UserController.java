package com.arcanus.todolist.user.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arcanus.todolist.user.dtos.CreateUserDTO;
import com.arcanus.todolist.user.dtos.UserDTO;
import com.arcanus.todolist.user.useCases.CreateUserUseCase;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private CreateUserUseCase createUserUseCase;


  @PostMapping
  public ResponseEntity<UserDTO> create(@RequestBody CreateUserDTO dto) {
    UserDTO createdUser = createUserUseCase.execute(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(createdUser.id()).toUri();
    return ResponseEntity.created(uri).body(createdUser);
  }

}
