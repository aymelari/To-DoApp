package org.example.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.todoapp.enums.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {
    private String title;
    private String description;
    private Status status;
    private Long category_id;

}
