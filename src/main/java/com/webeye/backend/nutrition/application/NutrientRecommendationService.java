package com.webeye.backend.nutrition.application;

import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.NutrientRecommendation;
import com.webeye.backend.nutrition.dto.request.NutrientRecommendationRequest;
import com.webeye.backend.nutrition.dto.response.NutrientRecommendationResponse;
import com.webeye.backend.nutrition.persistent.NutrientRecommendationRepository;
import com.webeye.backend.product.domain.ProductNutrient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NutrientRecommendationService {
    private static final int ZERO_RECOMMENDATION = 100;

    private final NutrientRecommendationRepository recommendationRepository;

    @Transactional
    public List<NutrientRecommendationResponse> analyzeNutrientSufficiency(NutrientRecommendationRequest request) {
        List<NutrientRecommendationResponse> overRecommendationNutrients = new ArrayList<>();
        int userAge = Year.now().getValue() - request.birthYear();

        for (ProductNutrient productNutrient : request.product().getNutrients()) {
            Nutrient nutrient = productNutrient.getNutrient();

            Optional<NutrientRecommendation> recommendationOpt = recommendationRepository
                    .findByNutrientIdAndGenderAndAge(nutrient.getId(), request.gender(), userAge);

            if (recommendationOpt.isEmpty()) {
                continue;
            }

            NutrientRecommendation recommendation = recommendationOpt.get();
            double percentage = getPercentageOfRecommendation(recommendation.getAmount(), productNutrient.getAmount());

            if (percentage >= 40.0) {
                overRecommendationNutrients.add(NutrientRecommendationResponse.builder()
                        .nutrientType(productNutrient.getNutrient().getType())
                        .percentage((int) percentage).build());
            }
        }
        return overRecommendationNutrients;
    }

    private double getPercentageOfRecommendation(double recommendationAmount, double productAmount) {
        if (recommendationAmount > 0) {
            return productAmount / recommendationAmount * 100;
        }
        return ZERO_RECOMMENDATION;
    }
}
