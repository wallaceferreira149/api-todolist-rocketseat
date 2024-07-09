package com.arcanus.todolist.filter.exceptions;

public class UserUnauthorizedException extends RuntimeException {

  public UserUnauthorizedException(String msg) {
    super(msg);
  }
}
