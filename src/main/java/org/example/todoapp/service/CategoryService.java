package org.example.todoapp.service;

import lombok.RequiredArgsConstructor;
import org.example.todoapp.dto.CategoryRequestDto;
import org.example.todoapp.dto.CategoryResponseDto;
import org.example.todoapp.entity.CategoryEntity;
import org.example.todoapp.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        CategoryEntity categoryEntity = modelMapper.map(categoryRequestDto, CategoryEntity.class);
        categoryRepository.save(categoryEntity);
        return modelMapper.map(categoryEntity, CategoryResponseDto.class);
    }

    public List<CategoryResponseDto> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
       return categoryEntities.stream().map(categoryEntity -> modelMapper.map(categoryEntity, CategoryResponseDto.class)).toList();
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
