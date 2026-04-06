package com.zorvyn.xpensify.modules.categories;

import com.zorvyn.xpensify.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category request) {
        return categoryRepository.save(request);
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category update(Long id, Category request) {
        Category existing = getById(id);

        if (request.getName() != null)
            existing.setName(request.getName());

        if (request.getType() != null)
            existing.setType(request.getType());

        if (request.getDescription() != null)
            existing.setDescription(request.getDescription());

        return categoryRepository.save(existing);
    }

    public void delete(Long id) {
        Category existing = getById(id);
        existing.setDeleted(true);
        categoryRepository.save(existing);
    }
}