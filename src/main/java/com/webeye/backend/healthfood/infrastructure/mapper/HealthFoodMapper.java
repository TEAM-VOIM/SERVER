package com.webeye.backend.healthfood.infrastructure.mapper;

import com.webeye.backend.healthfood.domain.HealthFood;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;

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
}
