package com.webeye.backend.nutrition.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "제품 영양 정보 응답")
@Builder
public record NutritionAiResponse(
        @Schema(description = "영양성분 정보 포함 여부")
        Boolean isNutrientIncluded,
        @Schema(description = "영양성분 도출 기준 함량")
        Integer nutrientReferenceAmount,

        @Schema(description = "나트륨 (mg)", example = "120.5")
        Double sodium,
        @Schema(description = "탄수화물 (g)", example = "25.0")
        Double carbohydrate,
        @Schema(description = "당류 (g)", example = "10.2")
        Double sugars,
        @Schema(description = "지방 (g)", example = "5.5")
        Double fat,
        @Schema(description = "트랜스지방 (g)", example = "0.0")
        Double transFat,
        @Schema(description = "포화지방 (g)", example = "1.2")
        Double saturatedFat,
        @Schema(description = "콜레스테롤 (mg)", example = "10.0")
        Double cholesterol,
        @Schema(description = "단백질 (g)", example = "8.5")
        Double protein,
        @Schema(description = "칼슘 (mg)", example = "200.0")
        Double calcium,
        @Schema(description = "인 (mg)", example = "150.0")
        Double phosphorus,
        @Schema(description = "나이아신 (mg)", example = "2.5")
        Double niacin,
        @Schema(description = "비타민 B (mg)", example = "1.5")
        Double vitaminB,
        @Schema(description = "비타민 E (mg)", example = "3.0")
        Double vitaminE
) {
}