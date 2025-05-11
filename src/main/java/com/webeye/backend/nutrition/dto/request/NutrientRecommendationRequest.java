package com.webeye.backend.nutrition.dto.request;

import com.webeye.backend.nutrition.domain.type.Gender;
import com.webeye.backend.product.domain.Product;
import lombok.Builder;

@Builder
public record NutrientRecommendationRequest(
    Product product,
    int birthYear,
    Gender gender
) {
}
