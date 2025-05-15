package com.webeye.backend.healthfood.infrastructure.mapper;

import com.webeye.backend.healthfood.domain.HealthFood;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.ProductHealthfood;

public class ProductHealthFoodMapper {

    public static ProductHealthfood toEntity(Product product, HealthFood healthFood) {
        return ProductHealthfood.builder()
                .product(product)
                .healthFood(healthFood)
                .build();
    }
}
