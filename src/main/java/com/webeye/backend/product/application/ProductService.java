package com.webeye.backend.product.application;

import com.webeye.backend.allergy.application.AllergyService;
import com.webeye.backend.allergy.type.AllergyType;
import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import com.webeye.backend.product.domain.ProductAllergy;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import com.webeye.backend.nutrition.application.NutritionService;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.dto.response.ProductResponse;
import com.webeye.backend.product.persistent.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final NutritionService nutritionService;
    private final AllergyService allergyService;

    private final ProductRepository productRepository;

    // TODO: 음식 제품 영양성분 분석
    @Transactional
    public ProductResponse analyzeFoodProduct(ProductAnalysisRequest request) {
        if (productRepository.existsById(request.productId())) {
            return ProductResponse.builder()
                    .allergyTypes(getAllergyResponse(request.productId(), request.allergies()))
                    .build();
        }

        Product product = Product.builder()
                .id(request.productId())
                .build();
        productRepository.save(product);

        nutritionService.saveProductNutrition(product, request);
        allergyService.saveProductAllergy(product, request);

        return ProductResponse.builder()
                .allergyTypes(getAllergyResponse(request.productId(), request.allergies()))
                .build();
    }

    private List<AllergyType> getAllergyResponse(String productId, List<AllergyType> userAllergies) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        return product.getAllergies().stream()
                .map(ProductAllergy::getAllergy)
                .distinct()
                .filter(userAllergies::contains)
                .toList();
    }
}
