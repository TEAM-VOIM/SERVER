package com.webeye.backend.rawmaterial.application;

import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import com.webeye.backend.nutrition.persistent.NutrientRepository;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.ProductNutrient;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.product.persistent.ProductRepository;
import com.webeye.backend.rawmaterial.domain.RawMaterial;
import com.webeye.backend.rawmaterial.persistent.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RawMaterialService {
    private final OpenAiClient openAiClient;

    private final RawMaterialRepository rawMaterialRepository;
    private final NutrientRepository nutrientRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void saveRawMaterialNutrition(Product product, FoodProductAnalysisRequest request) {
        String foodName = openAiClient.explainRawMaterial(request).name();
        Optional<RawMaterial> rawMaterialOpt = rawMaterialRepository.findFirstByNameContaining(foodName);
        rawMaterialOpt.ifPresentOrElse(rawMaterial -> {
            Map<NutrientType, Double> nutrientMap = convertToNutrientMap(rawMaterial);

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
        }, () -> log.warn("일치하는 원재료 데이터가 존재하지 않습니다."));
    }
    
    private Map<NutrientType, Double> convertToNutrientMap(RawMaterial rawMaterial) {
        Map<NutrientType, Double> map = new EnumMap<>(NutrientType.class);
        map.put(NutrientType.SODIUM, rawMaterial.getSodium());
        map.put(NutrientType.CARBOHYDRATE, rawMaterial.getCarbohydrate());
        map.put(NutrientType.SUGARS, rawMaterial.getSugars());
        map.put(NutrientType.FAT, rawMaterial.getFat());
        map.put(NutrientType.TRANS_FAT, rawMaterial.getTransFat());
        map.put(NutrientType.SATURATED_FAT, rawMaterial.getSaturatedFat());
        map.put(NutrientType.CHOLESTEROL, rawMaterial.getCholesterol());
        map.put(NutrientType.PROTEIN, rawMaterial.getProtein());
        map.put(NutrientType.CALCIUM, rawMaterial.getCalcium());
        map.put(NutrientType.PHOSPHORUS, rawMaterial.getPhosphorus());
        map.put(NutrientType.NIACIN, rawMaterial.getNiacin());
        return map;
    }

    private Nutrient findByType(NutrientType type) {
        return nutrientRepository.findByType(type)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영양소 타입: " + type));
    }
}
