package com.arcanus.todolist.user.entity;

import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private String username;
  private String name;
  private String password;

  @Column(name = "created_at")
  @CreationTimestamp
  private Instant createdAt;

  // @OneToMany(mappedBy="")
  // private Set<Task> tasks;

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(name, user.name)
        && Objects.equals(password, user.password) && Objects.equals(createdAt, user.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, name, password, createdAt);
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", username='" + getUsername() + "'" +
        ", name='" + getName() + "'" +
        ", password='" + getPassword() + "'" +
        ", createdAt='" + getCreatedAt() + "'" +
        "}";
  }
}
