package com.webeye.backend.healthfood.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "건강 기능 식품 원료명 추출")
public record HealthFoodAiResponse(
        List<String> itemNames
) {
}
