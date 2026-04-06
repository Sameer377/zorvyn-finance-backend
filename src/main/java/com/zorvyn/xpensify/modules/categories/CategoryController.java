package com.zorvyn.xpensify.modules.categories;

import com.zorvyn.xpensify.modules.categories.dto.CreateCategoryDto;
import com.zorvyn.xpensify.modules.categories.dto.ResponseCategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Category management endpoints")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Endpoint to create a new category")
    @PostMapping
    public ResponseEntity<ResponseCategoryDto> create(
            @RequestBody @Valid CreateCategoryDto request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.create(request));
    }

    @Operation(summary = "Endpoint to retrieve a category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @Operation(summary = "Endpoint to retrieve all categories")
    @GetMapping
    public ResponseEntity<List<ResponseCategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @Operation(summary = "Endpoint to update an existing category")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCategoryDto> update(
            @PathVariable Long id,
            @RequestBody CreateCategoryDto request
    ) {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @Operation(summary = "Endpoint to soft delete a category")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}