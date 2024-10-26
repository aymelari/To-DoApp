package org.example.todoapp.Controller;

import lombok.RequiredArgsConstructor;
import org.example.todoapp.dto.TaskRequestDto;
import org.example.todoapp.dto.TaskResponseDto;
import org.example.todoapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/new")
    public ResponseEntity <TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto taskResponseDto = taskService.createTask(taskRequestDto);
        return ResponseEntity.ok(taskResponseDto);
    }


    @PutMapping("/update{taskId}")
    public ResponseEntity <TaskResponseDto> updateTask(@RequestBody TaskRequestDto taskRequestDto,@RequestBody @PathVariable Long taskId) {
        TaskResponseDto taskResponseDto = taskService.updateTask(taskRequestDto, taskId);
        return ResponseEntity.ok(taskResponseDto);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable @RequestBody Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

     @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> assignCategorytoTask(@RequestBody @PathVariable Long taskId,@RequestBody Long categoryId){
        TaskResponseDto taskResponseDto = taskService.assignCategorytoTask(taskId, categoryId);
        return ResponseEntity.ok(taskResponseDto);

    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("get-by{id}")
    public ResponseEntity<List<TaskResponseDto>> getTaskByCategoryId(@PathVariable @RequestBody Long id){
        return ResponseEntity.ok(taskService.filterTaskByCategory(id));

    }
}
