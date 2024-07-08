package com.arcanus.todolist.user.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcanus.todolist.user.dtos.CreateUserDTO;
import com.arcanus.todolist.user.dtos.UserDTO;
import com.arcanus.todolist.user.entity.User;
import com.arcanus.todolist.user.exceptions.UserAlreadyExistsException;
import com.arcanus.todolist.user.repository.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class CreateUserUseCase {

  @Autowired
  private UserRepository userRepository;

  public UserDTO execute(CreateUserDTO dto) {
    Optional<User> checkUser = userRepository.findByUsername(dto.username());
    if (checkUser.isPresent() != false) {
      throw new UserAlreadyExistsException("username já existe.");
    }

    User entity = new User();
    entity.setUsername(dto.username());
    entity.setName(dto.name());

    String hash = BCrypt.withDefaults().hashToString(10, dto.password().toCharArray());
    entity.setPassword(hash);

    userRepository.save(entity);

    return new UserDTO(entity);
  }
}
