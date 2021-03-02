package com.hackerrank.eshopping.product.dashboard.repository;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory(String category, Sort sort);

    List<ProductEntity> findByCategoryAndAvailability(String category, Boolean availability, Sort sort);
}
