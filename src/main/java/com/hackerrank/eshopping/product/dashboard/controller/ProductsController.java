package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.entity.ProductEntity;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    final
    ProductService productService;
    final
    ModelMapper mapper;

    public ProductsController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        List<ProductEntity> entityList = productService.findAll();

        return entityList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{product_id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable("product_id") long id) {
        if (!productService.existsById(id)) {
            throw new NoSuchElementException("Product doesn't exists");
        }
        return convertEntityToDto(productService.findById(id));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public long save(@RequestBody Product product) {
        if (productService.existsById(product.getId())) {
            throw new IllegalArgumentException("Product already exists");
        }
        productService.save(convertDtoToEntity(product));
        return product.getId();
    }

    @PutMapping("/{product_id}")
    @ResponseStatus(HttpStatus.OK)
    public long updateById(@PathVariable("product_id") long id, @RequestBody Product product) {
        try {
            ProductEntity entity = productService.findById(id);
            productService.save(convertDtoToEntity(product, entity));
            return id;
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Cannot update");
        }
    }

    @GetMapping(value = "", params = "category")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findByCategory(@RequestParam String category) {

        category = category.replaceAll("%20", " ");
        List<ProductEntity> entityList = productService.findByCategory(category);
        return entityList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    @GetMapping(value = "", params = {"category", "availability"})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findByCategoryAndAvailability(@RequestParam String category, @RequestParam Boolean availability) {

        category = category.replaceAll("%20", " ");
        List<ProductEntity> entityList = productService.findByCategoryAndAvailability(category, availability);
        return entityList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private Product convertEntityToDto(ProductEntity entity) {
        return mapper.map(entity, Product.class);

    }

    private ProductEntity convertDtoToEntity(Product product) {
        return mapper.map(product, ProductEntity.class);
    }

    private ProductEntity convertDtoToEntity(Product product, ProductEntity entity) {
        mapper.map(product, entity);
        return entity;
    }

}
