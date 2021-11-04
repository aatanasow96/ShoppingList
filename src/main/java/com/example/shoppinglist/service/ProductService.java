package com.example.shoppinglist.service;

import com.example.shoppinglist.entity.enums.CategoryName;
import com.example.shoppinglist.entity.service.ProductServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void add(ProductServiceModel productServiceModel);

    BigDecimal getTotalSum();

    List<ProductViewModel> findAllProductsByCategoryName(CategoryName categoryName);

    void buyById(Long id);

    void buyAll();
}
