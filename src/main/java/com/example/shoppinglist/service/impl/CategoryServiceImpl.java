package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.entity.Category;
import com.example.shoppinglist.entity.enums.CategoryName;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void init() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        Category category = new Category(categoryName,
                                String.format("Description for %s", categoryName.name()));
                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public Category findByName(CategoryName name) {
        return categoryRepository.findByName(name).orElse(null);
    }
}
