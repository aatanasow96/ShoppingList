package com.example.shoppinglist.repository;

import com.example.shoppinglist.entity.Product;
import com.example.shoppinglist.entity.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT SUM(p.price) FROM Product p")
    BigDecimal findTotalProductSum();

    List<Product> findAllByCategory_Name(CategoryName categoryName);
}
