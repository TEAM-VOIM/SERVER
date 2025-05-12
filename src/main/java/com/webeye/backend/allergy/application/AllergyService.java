package com.webeye.backend.allergy.application;

import com.webeye.backend.allergy.dto.response.AllergyAiResponse;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.ProductAllergy;
import com.webeye.backend.product.persistent.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AllergyService {
    private final OpenAiClient openAiClient;
    private final ProductRepository productRepository;

    public AllergyAiResponse analyzeAllergy(FoodProductAnalysisRequest request) {
        return openAiClient.explainAllergy(request);
    }

    @Transactional
    public void saveProductAllergy(Product product, FoodProductAnalysisRequest request) {
        AllergyAiResponse response = analyzeAllergy(request);

        response.getAllergyTypes().forEach(allergyType ->
                product.addAllergy(
                        ProductAllergy.builder()
                                .product(product)
                                .allergy(allergyType)
                                .build()
                )
        );
        productRepository.save(product);
    }
}
