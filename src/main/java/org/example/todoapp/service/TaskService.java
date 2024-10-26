package org.example.todoapp.service;

import lombok.RequiredArgsConstructor;
import org.example.todoapp.dto.CategoryResponseDto;
import org.example.todoapp.dto.TaskRequestDto;
import org.example.todoapp.dto.TaskResponseDto;
import org.example.todoapp.entity.CategoryEntity;
import org.example.todoapp.entity.TaskEntity;
import org.example.todoapp.repository.CategoryRepository;
import org.example.todoapp.repository.TaskRepository;
import org.h2.util.Task;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        CategoryEntity categoryEntity= categoryRepository.findById(taskRequestDto.getCategory_id()).orElse(null);
        CategoryResponseDto categoryResponseDto=modelMapper.map(categoryEntity,CategoryResponseDto.class);

        TaskEntity taskEntity = TaskEntity.builder()
                .id(taskRequestDto.getCategory_id())
                .description(taskRequestDto.getDescription())
                .status(taskRequestDto.getStatus())
                .category(categoryEntity)
                .build();
        taskRepository.save(taskEntity);
        return TaskResponseDto.builder()
                .category(categoryResponseDto)
                .description(taskRequestDto.getDescription())
                .status(taskRequestDto.getStatus())
                .build();

    }

    public TaskResponseDto updateTask(TaskRequestDto taskRequestDto,Long taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        CategoryEntity categoryEntity= categoryRepository.findById(taskRequestDto.getCategory_id()).orElse(null);
        taskEntity.setDescription(taskRequestDto.getDescription());
        taskEntity.setStatus(taskRequestDto.getStatus());
        taskEntity.setCategory(categoryEntity);
        taskRepository.save(taskEntity);

        return modelMapper.map(taskEntity,TaskResponseDto.class);

    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);

    }

    public TaskResponseDto assignCategorytoTask(Long taskId,Long categoryId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElse(null);
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
        taskEntity.setCategory(categoryEntity);
        taskRepository.save(taskEntity);
        return modelMapper.map(taskEntity,TaskResponseDto.class);

    }

    public List<TaskResponseDto> getAllTasks() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
       return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskResponseDto.class)).toList();
    }

    public List<TaskResponseDto> filterTaskByCategory(Long categoryId) {
        CategoryEntity entity=categoryRepository.findById(categoryId).orElse(null);
        List<TaskEntity> taskEntities=taskRepository.findByCategory(entity);
        return taskEntities.stream().map(taskEntity -> modelMapper.map(taskEntity, TaskResponseDto.class)).toList();

    }



}
