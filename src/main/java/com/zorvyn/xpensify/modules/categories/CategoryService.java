package com.zorvyn.xpensify.modules.categories;

import com.zorvyn.xpensify.exception.NotFoundException;
import com.zorvyn.xpensify.modules.categories.dto.CategoryDtoMapper;
import com.zorvyn.xpensify.modules.categories.dto.CreateCategoryDto;
import com.zorvyn.xpensify.modules.categories.dto.ResponseCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseCategoryDto create(CreateCategoryDto request) {
        Category category = CategoryDtoMapper.toEntity(request);
        return CategoryDtoMapper.toResponseDto(categoryRepository.save(category));
    }

    public ResponseCategoryDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        if (Boolean.TRUE.equals(category.getDeleted())) {
            throw new NotFoundException("Category not found");
        }

        return CategoryDtoMapper.toResponseDto(category);
    }

    public List<ResponseCategoryDto> getAll() {
        return categoryRepository.findAll().stream()
                .filter(c -> !Boolean.TRUE.equals(c.getDeleted()))
                .map(CategoryDtoMapper::toResponseDto)
                .toList();
    }

    public ResponseCategoryDto update(Long id, CreateCategoryDto request) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        if (request.getName() != null)
            existing.setName(request.getName());

        if (request.getType() != null)
            existing.setType(request.getType());

        if (request.getDescription() != null)
            existing.setDescription(request.getDescription());

        return CategoryDtoMapper.toResponseDto(categoryRepository.save(existing));
    }

    public void delete(Long id) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        existing.setDeleted(true);
        categoryRepository.save(existing);
    }
}