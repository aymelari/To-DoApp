package org.example.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.todoapp.enums.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDto {
    private Long id;
    private String description;
    private Status status;
    private CategoryResponseDto category;
}
