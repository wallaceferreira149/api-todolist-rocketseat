package com.arcanus.todolist.user.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

  public UserAlreadyExistsException(String msg) {
    super(msg);
  }

}
