package com.webeye.backend.global.dto.response.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // health
    HEALTH_CHECK_SUCCESS(200, "Health Check Success"),

    // product
    FOOD_PRODUCT_ANALYSIS_SUCCESS(200, "Product Analysis Success"),

    // raw material
    RAW_MATERIAL_API_SUCCESS(200, "Raw Material API Success"),

    // nutrition
    NUTRITION_ANALYSIS_SUCCESS(200, "Nutrition analysis success"),

    // allergy
    ALLERGY_ANALYSIS_SUCCESS(200, "Allergy analysis success"),

    // cosmetic
    COSMETIC_ANALYSIS_SUCCESS(200, "Cosmetic analysis success"),

    // explanation
    PRODUCT_POINT_EXPLANATION_ANALYSIS_SUCCESS(200, "Product point explanation analysis success"),
    PRODUCT_DETAIL_EXPLANATION_ANALYSIS_SUCCESS(200, "Product detail explanation analysis success"),

    // review
    REVIEW_SUMMARY_SUCCESS(200, "Review summary success"),

    // health food
    HEALTH_FOOD_API_SUCCESS(200, "Health Food API success"),

    ;

    private final int status;
    private final String message;
}
