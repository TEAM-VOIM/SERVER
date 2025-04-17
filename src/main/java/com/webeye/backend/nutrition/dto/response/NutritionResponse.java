package com.webeye.backend.nutrition.dto.response;

import lombok.Builder;

@Builder
public record NutritionResponse (
        Double sodium,
        Double carbohydrate,
        Double sugars,
        Double fat,
        Double transFat,
        Double saturatedFat,
        Double cholesterol,
        Double protein,
        Double calcium,
        Double phosphorus,
        Double niacin,
        Double vitaminB,
        Double vitaminE
) {
}