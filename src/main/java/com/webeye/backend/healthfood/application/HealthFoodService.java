package com.webeye.backend.healthfood.application;

import com.webeye.backend.healthfood.domain.HealthFood;
import com.webeye.backend.healthfood.domain.HealthFoodKeyword;
import com.webeye.backend.healthfood.domain.Keyword;
import com.webeye.backend.healthfood.domain.type.HealthFoodType;
import com.webeye.backend.healthfood.dto.HealthFoodAiResponse;
import com.webeye.backend.healthfood.dto.HealthFoodKeywordResponse;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import com.webeye.backend.healthfood.infrastructure.client.HealthFoodClient;
import com.webeye.backend.healthfood.infrastructure.mapper.HealthFoodMapper;
import com.webeye.backend.healthfood.infrastructure.mapper.ProductHealthFoodMapper;
import com.webeye.backend.healthfood.infrastructure.persistence.HealthFoodRepository;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.ProductHealthfood;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.product.persistent.ProductHealthFoodRepository;
import com.webeye.backend.product.persistent.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final ProductRepository productRepository;
    private final HealthFoodRepository healthFoodRepository;
    private final ProductHealthFoodRepository productHealthFoodRepository;

    @Transactional
    public HealthFoodResponse.I2710 callHealthFoodApi() {
        HealthFoodResponse response = healthFoodClient.getHealthFood(serviceKey, serviceId, "json", "1", "478");

        HealthFoodResponse.I2710 i2710 = response.I2710();

        List<HealthFoodResponse.Row> rows = i2710.row();

        List<HealthFood> healthFoods = HealthFoodMapper.toEntityList(rows);

        healthFoodRepository.saveAll(healthFoods);

        return HealthFoodMapper.toResponseList(healthFoods, i2710.totalCount());
    }

    @Transactional
    public HealthFoodKeywordResponse analyzeAndSaveHealthFood(FoodProductAnalysisRequest request) {
        Product product = productRepository.findById(request.productId())
                .orElseGet(() -> productRepository.save(
                        Product.builder()
                                .id(request.productId())
                                .build()
                ));
        HealthFoodAiResponse response = analyzeHealthFood(request);

        List<String> dbItemNames = healthFoodRepository.findAllItemNames();
        List<String> itemNames = matchItemNames(response.itemNames(), dbItemNames);

        List<HealthFood> healthFoods = healthFoodRepository.findByItemNameIn(itemNames);

        saveProductHealthFood(product, healthFoods);

        List<HealthFoodType> types = mapHealthFoodTypes(healthFoods);

        return HealthFoodMapper.toResponse(types);
    }

    public HealthFoodAiResponse analyzeHealthFood(FoodProductAnalysisRequest request) {
        return openAiClient.explainHealthFood(request);
    }

    @Transactional
    public void saveProductHealthFood(Product product, List<HealthFood> healthFoods) {
        List<ProductHealthfood> productHealthFoods = healthFoods.stream()
                .map(healthFood -> ProductHealthFoodMapper.toEntity(product, healthFood))
                .toList();

        productHealthFoodRepository.saveAll(productHealthFoods);
    }

    private List<HealthFoodType> mapHealthFoodTypes(List<HealthFood> healthFoods) {
        return healthFoods.stream()
                .flatMap(healthFood -> healthFood.getHealthFoodKeywords().stream())
                .map(HealthFoodKeyword::getKeyword)
                .map(Keyword::getType)
                .distinct()
                .toList();
    }

    private List<String> matchItemNames(List<String> aiItemNames, List<String> dbItemNames) {
        return aiItemNames.stream()
                .flatMap(ai -> dbItemNames.stream()
                        .filter(ai::contains))
                .distinct()
                .toList();
    }
}
