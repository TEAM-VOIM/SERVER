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

    // product detail
    PRODUCT_DETAIL_EXPLANATION_ANALYSIS_SUCCESS(200, "Product detail explanation analysis success"),

    // explanation
    IMAGE_ANALYSIS_SUCCESS(200, "ImageAnalysis Success"),

    // cosmetic
    COSMETIC_ANALYSIS_SUCCESS(200, "Cosmetic analysis success"),

    // review
    REVIEW_SUMMARY_SUCCESS(200, "Review summary success"),

    // health food
    HEALTH_FOOD_API_SUCCESS(200, "Health Food API success"),
    HEALTH_FOOD_ANALYSIS_SUCCESS(200, "Health Food analysis success"),

    ;

    private final int status;
    private final String message;
}
