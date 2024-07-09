package com.arcanus.todolist.task.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcanus.todolist.task.dto.CreateTaskDTO;
import com.arcanus.todolist.task.dto.TaskDTO;
import com.arcanus.todolist.task.entity.Task;
import com.arcanus.todolist.task.repository.TaskRepository;

@Service
public class CreateTaskUseCase {

  @Autowired
  private TaskRepository repository;

  public TaskDTO execute(CreateTaskDTO dto, Long userId) {
    Task entity = new Task();
    entity.setDescription(dto.description());
    entity.setTitle(dto.title());
    entity.setPriority(dto.priority());
    entity.setStartDate(dto.startDate());
    entity.setFinishDate(dto.finishDate());
    entity.setUserId(userId);

    entity = repository.save(entity);

    return new TaskDTO(entity);
  }
}
