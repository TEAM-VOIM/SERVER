package com.webeye.backend.nutrition.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record NutrientResponse(
        @Schema(description = "영양성분 도출 기준 함량(단위: g)")
        Integer nutrientReferenceAmount,

        @Schema(description = "영양성분 권장량을 넘는 성분")
        List<NutrientRecommendationResponse> overRecommendationNutrients
) {
}
