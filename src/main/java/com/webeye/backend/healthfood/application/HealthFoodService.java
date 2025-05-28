package com.webeye.backend.healthfood.application;

import com.webeye.backend.healthfood.domain.HealthFood;
import com.webeye.backend.healthfood.dto.HealthFoodAiResponse;
import com.webeye.backend.healthfood.dto.HealthFoodKeywordResponse;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import com.webeye.backend.healthfood.infrastructure.client.HealthFoodClient;
import com.webeye.backend.healthfood.infrastructure.mapper.HealthFoodMapper;
import com.webeye.backend.healthfood.infrastructure.mapper.ProductHealthFoodMapper;
import com.webeye.backend.healthfood.infrastructure.persistence.HealthFoodRepository;
import com.webeye.backend.imageanalysis.infrastructure.ImageUrlExtractor;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.ProductHealthfood;
import com.webeye.backend.product.domain.type.ProductType;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.product.persistent.ProductHealthFoodRepository;
import com.webeye.backend.product.persistent.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        Product product = findOrCreateProduct(request.productId());

        // DB에 있을 경우, DB에서 조회 후 호출
        if (product.getHealthFoods() != null && !product.getHealthFoods().isEmpty()) {
            return HealthFoodMapper.toResponseFromProduct(product);
        }
        HealthFoodAiResponse response = analyzeHealthFood(request);

        List<String> dbItemNames = healthFoodRepository.findAllItemNames();
        List<String> itemNames = matchItemNames(response.itemNames(), dbItemNames);

        List<HealthFood> healthFoods = healthFoodRepository.findByItemNameIn(itemNames);

        saveProductHealthFood(product, healthFoods);

        return HealthFoodMapper.toResponseFromHealthFoods(healthFoods);
    }

    public HealthFoodAiResponse analyzeHealthFood(FoodProductAnalysisRequest request) {
        return openAiClient.explainHealthFood(ImageUrlExtractor.extractImageUrlFromHtml(request.html()));
    }

    @Transactional
    public void saveProductHealthFood(Product product, List<HealthFood> healthFoods) {
        List<ProductHealthfood> productHealthFoods = new ArrayList<>();

        for (HealthFood healthFood : healthFoods) {
            ProductHealthfood productHealthfood = ProductHealthFoodMapper.toEntity(product, healthFood);

            product.addHealthFood(productHealthfood);
            productHealthFoods.add(productHealthfood);
        }
        productHealthFoodRepository.saveAll(productHealthFoods);
    }

    @Transactional
    public Product findOrCreateProduct(String productId) {
        return productRepository.findByIdWithHealthFoods(productId)
                .orElseGet(() -> productRepository.save(
                        Product.builder()
                                .id(productId)
                                .productType(ProductType.HEALTH_FOOD)
                                .build()));
    }

    private List<String> matchItemNames(List<String> aiItemNames, List<String> dbItemNames) {
        return aiItemNames.stream()
                .filter(ai -> dbItemNames.stream().anyMatch(ai::contains))
                .distinct()
                .toList();
    }
}
