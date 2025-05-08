package com.webeye.backend.nutrition.dto.response;

import com.webeye.backend.nutrition.domain.type.NutrientType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "영양성분 권장량에 대한 비율 응답")
@Builder
public record NutrientRecommendationResponse(
        @Schema(description = "영양성분 이름")
        NutrientType nutrientType,

        @Schema(description = "권장량에 대한 영양성분의 비율")
        int percentage
) {
}
