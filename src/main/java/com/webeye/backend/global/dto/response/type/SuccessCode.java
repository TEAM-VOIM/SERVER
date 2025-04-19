package com.webeye.backend.global.dto.response.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // health
    HEALTH_CHECK_SUCCESS(200, "Health Check Success"),

    // nutrition
    NUTRITION_ANALYSIS_SUCCESS(200, "Nutrition analysis success"),

    // allergy
    ALLERGY_ANALYSIS_SUCCESS(200, "Allergy analysis success"),

    // explanation
    PRODUCT_POINT_EXPLANATION_ANALYSIS_SUCCESS(200, "Product point explanation analysis success"),
    PRODUCT_DETAIL_EXPLANATION_ANALYSIS_SUCCESS(200, "Product detail explanation analysis success"),

    ;

    private final int status;
    private final String message;
}
