package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.entity.Product;
import com.example.shoppinglist.entity.enums.CategoryName;
import com.example.shoppinglist.entity.service.ProductServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.CategoryService;
import com.example.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @Override
    public void add(ProductServiceModel productServiceModel) {
        Product product = mapper.map(productServiceModel, Product.class);
        product.setCategory(categoryService.findByName(productServiceModel.getCategory()));
        productRepository.save(product);
    }

    @Override
    public BigDecimal getTotalSum() {
        BigDecimal totalProductSum = productRepository.findTotalProductSum();
        return totalProductSum == null ? BigDecimal.valueOf(0) : totalProductSum;
    }

    @Override
    public List<ProductViewModel> findAllProductsByCategoryName(CategoryName categoryName) {
        return productRepository.findAllByCategory_Name(categoryName)
                .stream()
                .map(product -> mapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        productRepository.deleteAll();
    }
}
