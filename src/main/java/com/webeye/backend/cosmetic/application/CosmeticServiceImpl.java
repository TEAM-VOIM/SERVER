package com.webeye.backend.cosmetic.application;

import com.webeye.backend.cosmetic.domain.CosmeticIngredient;
import com.webeye.backend.cosmetic.domain.Ingredient;
import com.webeye.backend.cosmetic.domain.type.IngredientType;
import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.cosmetic.infrastructure.mapper.CosmeticIngredientMapper;
import com.webeye.backend.cosmetic.infrastructure.persistence.CosmeticIngredientRepository;
import com.webeye.backend.cosmetic.infrastructure.persistence.IngredientRepository;
import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import com.webeye.backend.imageanalysis.infrastructure.ImageUrlExtractor;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.type.ProductType;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import com.webeye.backend.product.persistent.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CosmeticServiceImpl implements CosmeticService {

    private final OpenAiClient openAiClient;
    private final ImageUrlExtractor imageUrlExtractor;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final CosmeticIngredientRepository cosmeticIngredientRepository;

    @Override
    @Transactional
    public CosmeticResponse analyzeCosmetic(ProductAnalysisRequest request) {
        Product product = findOrCreateProduct(request.productId());

        // DB에 있을 경우, DB에서 조회 후 호출
        if (product.getCosmeticIngredients() != null && !product.getCosmeticIngredients().isEmpty()) {
            return CosmeticIngredientMapper.toResponse(product.getCosmeticIngredients());
        }

        CosmeticResponse response = openAiClient.explainCosmetic(
                imageUrlExtractor.extractImageUrlFromHtml(request.html())
        );

        Map<IngredientType, Boolean> resultMap = convertToEnumMap(response);

        resultMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .forEach(entry -> {
                    Ingredient ingredient = ingredientRepository.findByIngredientType(entry.getKey())
                            .orElseThrow(() -> new BusinessException(ErrorCode.COSMETIC_INGREDIENT_NOT_FOUND));

                    CosmeticIngredient cosmeticIngredient = CosmeticIngredientMapper.toEntity(product, ingredient);
                    cosmeticIngredientRepository.save(cosmeticIngredient);
                });

        return response;
    }

    @Transactional
    public Product findOrCreateProduct(String productId) {
        return productRepository.findByIdWithCosmeticIngredients(productId)
                .orElseGet(() -> productRepository.save(
                        Product.builder()
                                .id(productId)
                                .productType(ProductType.COSMETIC)
                                .build()));
    }

    private Map<IngredientType, Boolean> convertToEnumMap(CosmeticResponse response) {
        return Map.ofEntries(
                Map.entry(IngredientType.AVOBENZONE, response.avobenzone()),
                Map.entry(IngredientType.ISOPROPYL_ALCOHOL, response.isopropylAlcohol()),
                Map.entry(IngredientType.SODIUM_LAURYL_SULFATE, response.sodiumLaurylSulfate()),
                Map.entry(IngredientType.TRIETHANOLAMINE, response.triethanolamine()),
                Map.entry(IngredientType.POLYETHYLENE_GLYCOL, response.polyethyleneGlycol()),
                Map.entry(IngredientType.SYNTHETIC_COLORANT, response.syntheticColorant()),
                Map.entry(IngredientType.ISOPROPYL_METHYLPHENOL, response.isopropylMethylphenol()),
                Map.entry(IngredientType.SORBIC_ACID, response.sorbicAcid()),
                Map.entry(IngredientType.HORMONE, response.hormone()),
                Map.entry(IngredientType.DIBUTYL_HYDROXYTOLUENE, response.dibutylHydroxyToluene()),
                Map.entry(IngredientType.PARABENS, response.parabens()),
                Map.entry(IngredientType.TRICLOSAN, response.triclosan()),
                Map.entry(IngredientType.BUTYLATED_HYDROXYANISOLE, response.butylatedHydroxyanisole()),
                Map.entry(IngredientType.OXYBENZONE, response.oxybenzone()),
                Map.entry(IngredientType.IMIDAZOLIDINYL_UREA, response.imidazolidinylUrea()),
                Map.entry(IngredientType.MINERAL_OIL, response.mineralOil()),
                Map.entry(IngredientType.THYMOL, response.thymol()),
                Map.entry(IngredientType.TRIISOPROPANOLAMINE, response.triisopropanolamine()),
                Map.entry(IngredientType.SYNTHETIC_FRAGRANCE, response.syntheticFragrance()),
                Map.entry(IngredientType.PHENOXYETHANOL, response.phenoxyethanol())
        );
    }
}
