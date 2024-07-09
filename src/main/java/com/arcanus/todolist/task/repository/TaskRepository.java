package com.arcanus.todolist.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcanus.todolist.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByUserId(Long userId);

}
