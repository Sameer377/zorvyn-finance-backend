package com.zorvyn.xpensify.modules.categories.dto;

import com.zorvyn.xpensify.modules.categories.Category;

import java.util.List;

public class CategoryDtoMapper {

    public static Category toEntity(CreateCategoryDto dto) {
        if (dto == null) return null;

        return Category.builder()
                .name(dto.getName())
                .type(dto.getType())
                .description(dto.getDescription())
                .build();
    }

    public static ResponseCategoryDto toResponseDto(Category category) {
        if (category == null) return null;

        return ResponseCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .build();
    }

    public static List<ResponseCategoryDto> toResponseDtoList(List<Category> categories) {
        return categories.stream()
                .map(CategoryDtoMapper::toResponseDto)
                .toList();
    }
}