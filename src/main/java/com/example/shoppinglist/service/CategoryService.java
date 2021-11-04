package com.example.shoppinglist.service;

import com.example.shoppinglist.entity.Category;
import com.example.shoppinglist.entity.enums.CategoryName;

public interface CategoryService {
    void init();

    Category findByName(CategoryName category);
}
