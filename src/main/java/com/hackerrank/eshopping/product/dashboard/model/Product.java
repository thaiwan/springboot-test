package com.hackerrank.eshopping.product.dashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String category;
    //my suggestion - use BigDecimal for prices
    @JsonProperty("retail_price")
    private Double retailPrice;
    @JsonProperty("discounted_price")
    private Double discountedPrice;
    private Boolean availability;

}
