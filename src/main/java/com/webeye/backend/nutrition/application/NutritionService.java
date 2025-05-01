package com.webeye.backend.nutrition.application;

import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import com.webeye.backend.nutrition.dto.response.NutritionAiResponse;
import com.webeye.backend.nutrition.persistent.NutrientRepository;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.ProductNutrient;
import com.webeye.backend.product.persistent.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NutritionService {
    private final OpenAiClient openAiClient;

    private final NutrientRepository nutrientRepository;
    private final ProductRepository productRepository;

    public NutritionAiResponse analyzeNutrition(ProductAnalysisRequest request) {
        return openAiClient.explainNutrition(request);
    }

    public Nutrient findByType(NutrientType type) {
        return nutrientRepository.findByName(type)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
    }

    @Transactional
    public void saveProductNutrition(Product product, ProductAnalysisRequest request) {
        NutritionAiResponse response = analyzeNutrition(request);
        Map<NutrientType, Double> nutrientMap = extractNutrientMap(response);

        nutrientMap.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .forEach(e -> {
                    Nutrient nutrient = findByType(e.getKey());
                    if (nutrient == null) return;
                    product.addNutrient(
                            ProductNutrient.builder()
                                    .product(product)
                                    .nutrient(nutrient)
                                    .amount(e.getValue())
                                    .build()
                    );
                });
        productRepository.save(product);
    }

    private Map<NutrientType, Double> extractNutrientMap(NutritionAiResponse response) {
        Map<NutrientType, Double> map = new EnumMap<>(NutrientType.class);

        map.put(NutrientType.SODIUM, response.sodium());
        map.put(NutrientType.CARBOHYDRATE, response.carbohydrate());
        map.put(NutrientType.SUGARS, response.sugars());
        map.put(NutrientType.FAT, response.fat());
        map.put(NutrientType.TRANS_FAT, response.transFat());
        map.put(NutrientType.SATURATED_FAT, response.saturatedFat());
        map.put(NutrientType.CHOLESTEROL, response.cholesterol());
        map.put(NutrientType.PROTEIN, response.protein());
        map.put(NutrientType.CALCIUM, response.calcium());
        map.put(NutrientType.PHOSPHORUS, response.phosphorus());
        map.put(NutrientType.NIACIN, response.niacin());
        map.put(NutrientType.VITAMIN_B, response.vitaminB());
        map.put(NutrientType.VITAMIN_E, response.vitaminE());

        return map;
    }
}
