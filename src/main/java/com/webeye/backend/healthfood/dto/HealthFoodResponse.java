package com.webeye.backend.healthfood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record HealthFoodResponse(
        I2710 I2710
) {
    public record I2710(
            @JsonProperty("total_count")
            Integer totalCount,
            List<Row> row
    ){}

    public record Row(
            String PRDCT_NM,
            String PRIMARY_FNCLTY
    ){}
}
