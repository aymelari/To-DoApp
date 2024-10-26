package org.example.todoapp.repository;

import org.example.todoapp.entity.CategoryEntity;
import org.example.todoapp.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByCategory(CategoryEntity category);
}
