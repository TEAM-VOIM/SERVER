package com.webeye.backend.product.dto.response;

import com.webeye.backend.allergy.type.AllergyType;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "제품 분석 응답 (SODIUM: 나트륨, CARBOHYDRATE: 탄수화물, SUGARS: 당류, FAT: 지방, TRANS_FAT: 트랜스지방, SATURATED_FAT: 포화지방, CHOLESTEROL: 콜레스테롤, PROTEIN: 단백질, CALCIUM: 칼슘, PHOSPHORUS: 인, NIACIN: 나이아신, VITAMIN_B: 비타민 B, VITAMIN_E: 비타민 E)")
@Builder
public record ProductResponse (
        @Schema(description = "포함된 주의 영양성분")
        List<NutrientType> nutrientTypes,

        @Schema(description = "포함된 알레르기 유발 성분")
        List<AllergyType> allergyTypes
) {
}
