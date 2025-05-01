package com.webeye.backend.product.dto.response;

import com.webeye.backend.allergy.type.AllergyType;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "제품 분석 응답")
@Builder
public record ProductResponse (
        @Schema(description = "포함된 주의 영양성분")
        List<NutrientType> nutrientTypes,

        @Schema(description = "포함된 알레르기 유발 성분")
        List<AllergyType> allergyTypes
) {
}
