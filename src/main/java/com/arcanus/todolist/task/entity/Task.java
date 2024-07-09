package com.arcanus.todolist.task.entity;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Task implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(length = 100)
  private String title;

  private LocalDateTime startDate;
  private LocalDateTime finishDate;

  @Column(length = 50)
  private String priority;

  @CreationTimestamp
  private Instant createdAt;

  // @ManyToOne
  // @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private Long userId;

}
