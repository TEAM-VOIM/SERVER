package com.webeye.backend.healthfood.infrastructure.mapper;

import com.webeye.backend.healthfood.domain.HealthFood;
import com.webeye.backend.healthfood.domain.HealthFoodKeyword;
import com.webeye.backend.healthfood.domain.Keyword;
import com.webeye.backend.healthfood.domain.type.HealthFoodType;
import com.webeye.backend.healthfood.dto.HealthFoodKeywordResponse;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.ProductHealthfood;

import java.util.List;
import java.util.stream.Collectors;

public class HealthFoodMapper {

    public static HealthFood toEntity(HealthFoodResponse.Row row) {
        return HealthFood.builder()
                .itemName(row.PRDCT_NM())
                .functionality(row.PRIMARY_FNCLTY())
                .build();
    }

    public static List<HealthFood> toEntityList(List<HealthFoodResponse.Row> rows) {
        return rows.stream()
                .map(HealthFoodMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static HealthFoodResponse.Row toResponse(HealthFood healthFood) {
        return new HealthFoodResponse.Row(
                healthFood.getItemName(),
                healthFood.getFunctionality()
        );
    }

    public static HealthFoodResponse.I2710 toResponseList(List<HealthFood> healthFoodList, Integer totalCount) {
        List<HealthFoodResponse.Row> i2710List = healthFoodList.stream()
                .map(HealthFoodMapper::toResponse)
                .collect(Collectors.toList());

        return new HealthFoodResponse.I2710(totalCount, i2710List);
    }

    public static HealthFoodKeywordResponse toResponseFromProduct(Product product) {
        List<HealthFood> healthFoods = product.getHealthFoods().stream()
                .map(ProductHealthfood::getHealthFood)
                .toList();

        return toResponseFromHealthFoods(healthFoods);
    }

    public static HealthFoodKeywordResponse toResponseFromHealthFoods(List<HealthFood> healthFoods) {
        List<HealthFoodType> types = extractTypes(healthFoods);

        return HealthFoodKeywordResponse.builder()
                .types(types)
                .build();
    }

    private static List<HealthFoodType> extractTypes(List<HealthFood> healthFoods) {
        return healthFoods.stream()
                .flatMap(healthFood -> healthFood.getHealthFoodKeywords().stream())
                .map(HealthFoodKeyword::getKeyword)
                .map(Keyword::getType)
                .distinct()
                .toList();
    }
}
