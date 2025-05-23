package com.webeye.backend.cosmetic.application;

import com.webeye.backend.cosmetic.domain.Cosmetic;
import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.imageanalysis.infrastructure.ImageUrlExtractor;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.domain.type.ProductType;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import com.webeye.backend.product.persistent.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CosmeticServiceImpl implements CosmeticService {

    private final OpenAiClient openAiClient;
    private final ImageUrlExtractor imageUrlExtractor;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CosmeticResponse analyzeCosmetic(ProductAnalysisRequest request) {
        Product product = findOrCreateProduct(request.productId());

        Cosmetic existingCosmetic = product.getCosmetic();
        if (existingCosmetic != null) {
            return null;
        }
        return openAiClient.explainCosmetic(imageUrlExtractor.extractImageUrlFromHtml(request.html()));
    }

    @Transactional
    public Product findOrCreateProduct(String productId) {
        return productRepository.findByIdWithCosmetic(productId)
                .orElseGet(() -> productRepository.save(
                        Product.builder()
                                .id(productId)
                                .productType(ProductType.COSMETIC)
                                .build()));
    }
}
