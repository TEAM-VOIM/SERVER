package com.webeye.backend.healthfood.application;

import com.webeye.backend.healthfood.domain.HealthFood;
import com.webeye.backend.healthfood.domain.HealthFoodKeyword;
import com.webeye.backend.healthfood.domain.Keyword;
import com.webeye.backend.healthfood.domain.type.HealthFoodType;
import com.webeye.backend.healthfood.dto.HealthFoodAiResponse;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import com.webeye.backend.healthfood.infrastructure.client.HealthFoodClient;
import com.webeye.backend.healthfood.infrastructure.mapper.HealthFoodMapper;
import com.webeye.backend.healthfood.infrastructure.persistence.HealthFoodRepository;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HealthFoodService {

    @Value("${open-api.health-food.service-id}")
    private String serviceId;

    @Value("${open-api.health-food.service-key}")
    private String serviceKey;

    private final OpenAiClient openAiClient;
    private final HealthFoodClient healthFoodClient;
    private final HealthFoodRepository healthFoodRepository;

    @Transactional
    public HealthFoodResponse.I2710 callHealthFoodApi() {
        HealthFoodResponse response = healthFoodClient.getHealthFood(serviceKey, serviceId, "json", "1", "478");

        HealthFoodResponse.I2710 i2710 = response.I2710();

        List<HealthFoodResponse.Row> rows = i2710.row();

        List<HealthFood> healthFoods = HealthFoodMapper.toEntityList(rows);

        healthFoodRepository.saveAll(healthFoods);

        return HealthFoodMapper.toResponseList(healthFoods, i2710.totalCount());
    }

    @Transactional(readOnly = true)
    public HealthFoodAiResponse analyzeHealthFood(ProductAnalysisRequest request) {
        List<String> ingredients = healthFoodRepository.findAllItemNames();

        String extractedText = openAiClient.explainHealthFood(request, ingredients);

        List<String> matchedIngredients = Arrays.stream(extractedText.split(","))
                .map(String::trim)
                .filter(ingredients::contains)
                .distinct()
                .toList();

        List<HealthFoodType> types = mapHealthFoodTypes(matchedIngredients);

        return HealthFoodMapper.toResponse(types);
    }

    private List<HealthFoodType> mapHealthFoodTypes(List<String> ingredients) {
        return ingredients.stream()
                .flatMap(ingredient -> healthFoodRepository.findByItemNameContaining(ingredient).stream())
                .flatMap(healthFood -> healthFood.getHealthFoodKeywords().stream())
                .map(HealthFoodKeyword::getKeyword)
                .map(Keyword::getType)
                .distinct()
                .toList();
    }
}
