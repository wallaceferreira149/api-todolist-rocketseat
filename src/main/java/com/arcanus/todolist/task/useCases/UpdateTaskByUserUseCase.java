package com.arcanus.todolist.task.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcanus.todolist.task.dto.TaskDTO;
import com.arcanus.todolist.task.entity.Task;
import com.arcanus.todolist.task.exceptions.TaskNotFoundException;
import com.arcanus.todolist.task.repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UpdateTaskByUserUseCase {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ListTasksByUserUseCase listTasksByUserUseCase;

  public List<Task> execute(TaskDTO dto, Long userId, Long taskId) {
    try {
      Task entity = taskRepository.getReferenceById(taskId);
      entity.setDescription(dto.description());
      entity.setTitle(dto.title());
      entity.setPriority(dto.priority());
      entity.setStartDate(dto.startDate());
      entity.setFinishDate(dto.finishDate());
      entity.setUserId(userId);

      entity = taskRepository.save(entity);

      var tasks = listTasksByUserUseCase.execute(userId);

      return tasks;

    } catch (EntityNotFoundException e) {
      throw new TaskNotFoundException("Tarefa não encontrada");
    }
  }
}
