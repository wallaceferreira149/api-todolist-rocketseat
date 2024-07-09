package com.arcanus.todolist.task.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcanus.todolist.task.entity.Task;
import com.arcanus.todolist.task.repository.TaskRepository;

@Service
public class ListTasksByUserUseCase {

  @Autowired
  private TaskRepository taskRepository;

  public List<Task> execute(Long userId) {
    List<Task> tasks = taskRepository.findByUserId(userId);
    return tasks;
  }
}
