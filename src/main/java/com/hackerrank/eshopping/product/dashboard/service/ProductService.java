package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findAll();

    ProductEntity findById(long id);

    void save(ProductEntity productEntity);

    boolean existsById(long id);

    List<ProductEntity> findByCategory(String category);

    List<ProductEntity> findByCategoryAndAvailability(String category, Boolean availability);

}
