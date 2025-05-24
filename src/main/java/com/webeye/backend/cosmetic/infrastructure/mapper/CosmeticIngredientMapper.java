package com.webeye.backend.cosmetic.infrastructure.mapper;

import com.webeye.backend.cosmetic.domain.CosmeticIngredient;
import com.webeye.backend.cosmetic.domain.Ingredient;
import com.webeye.backend.product.domain.Product;

public class CosmeticIngredientMapper {

    public static CosmeticIngredient toEntity(Product product, Ingredient ingredient) {
        return CosmeticIngredient.builder()
                .product(product)
                .ingredient(ingredient)
                .build();
    }
}
