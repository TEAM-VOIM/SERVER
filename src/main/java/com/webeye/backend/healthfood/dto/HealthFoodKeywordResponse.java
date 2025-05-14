package com.webeye.backend.healthfood.dto;

import com.webeye.backend.healthfood.domain.type.HealthFoodType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "건강 기능 식품 키워드 분석")
public record HealthFoodKeywordResponse(
        List<HealthFoodType> types
) {
}
