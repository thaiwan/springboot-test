package com.hackerrank.eshopping.product.dashboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String category;
    private Double retailPrice;
    private Double discountedPrice;
    private Boolean availability;

    @Formula("((retail_price - discounted_price)/retail_price)*100")
    @Setter(value = AccessLevel.PRIVATE)
    private Integer percentage;

}
