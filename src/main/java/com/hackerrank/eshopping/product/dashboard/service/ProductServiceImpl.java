package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductEntity> findAll() {
        List<ProductEntity> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public ProductEntity findById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product doesn't exists"));
    }

    @Override
    public void save(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    @Override
    public boolean existsById(long id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<ProductEntity> findByCategory(String category) {
        Sort sort = Sort.by("availability").descending()
                .and(Sort.by("discountedPrice").ascending()
                        .and(Sort.by("id").ascending()));
        return productRepository.findByCategory(category, sort);

    }

    @Override
    public List<ProductEntity> findByCategoryAndAvailability(String category, Boolean availability) {
        List<ProductEntity> products = productRepository.findByCategoryAndAvailability(category, availability, Sort.unsorted());

        Comparator<ProductEntity> customSort = Comparator
                .comparing(ProductEntity::getPercentage).reversed()
                .thenComparing(ProductEntity::getDiscountedPrice)
                .thenComparing(ProductEntity::getId);

        return products.stream()
                .sorted(customSort)
                .collect(Collectors.toList());
    }
}
