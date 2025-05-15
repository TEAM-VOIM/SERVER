package com.webeye.backend.nutrition.application;

import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import com.webeye.backend.nutrition.dto.response.NutritionAiResponse;
import com.webeye.backend.nutrition.persistent.NutrientRepository;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.ProductNutrient;
import com.webeye.backend.product.persistent.ProductRepository;
import com.webeye.backend.rawmaterial.application.RawMaterialService;
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
    private final RawMaterialService rawMaterialService;

    private final NutrientRepository nutrientRepository;
    private final ProductRepository productRepository;

    public Nutrient findByType(NutrientType type) {
        return nutrientRepository.findByType(type)
                .orElseThrow(() -> new BusinessException(ErrorCode.NUTRIENT_NOT_FOUND));
    }

    @Transactional
    public void saveProductNutrition(Product product, FoodProductAnalysisRequest request) {
        NutritionAiResponse response = openAiClient.explainNutrition(request);
        if (Boolean.TRUE.equals(response.isNutrientIncluded())) {
            Map<NutrientType, Double> nutrientMap = extractNutrientMap(response);

            nutrientMap.forEach((type, amount) -> {
                if (amount == null) return;
                Nutrient nutrient = findByType(type);
                product.addNutrient(
                        ProductNutrient.builder()
                                .product(product)
                                .nutrient(nutrient)
                                .amount(amount)
                                .build()
                );
            });
            productRepository.save(product);
            return;
        }
        rawMaterialService.saveRawMaterialNutrition(product, request);
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
