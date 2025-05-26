package com.webeye.backend.productdetail.application;

import com.webeye.backend.imageanalysis.infrastructure.ImageUrlExtractor;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.productdetail.dto.request.ProductDetailAnalysisRequest;
import com.webeye.backend.productdetail.dto.response.DetailExplanationResponse;
import com.webeye.backend.product.persistent.ProductRepository;
import com.webeye.backend.productdetail.domain.ProductDetail;
import com.webeye.backend.productdetail.domain.type.OutlineType;
import com.webeye.backend.productdetail.dto.response.AllDetailExplanationResponse;
import com.webeye.backend.productdetail.persistent.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDetailService {
    private final OpenAiClient openAiClient;

    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;

    @Transactional
    public DetailExplanationResponse analyzeProductDetail(OutlineType outline, ProductDetailAnalysisRequest request) {
        Optional<ProductDetail> productDetailOpt = productDetailRepository.findByProductIdAndOutline(request.productId(), outline);
        if (productDetailOpt.isEmpty()) {
            AllDetailExplanationResponse details = openAiClient.explainProductAllDetail(ImageUrlExtractor.extractImageUrlFromHtml(request.html()));
            log.info("product detail response:\n{}", details.toString());

            saveProductDetails(request.productId(), details);
            return new DetailExplanationResponse(getContentByOutline(details, outline));
        }
        return new DetailExplanationResponse(productDetailOpt.get().getContent());
    }

    @Transactional
    public void saveProductDetails(String id, AllDetailExplanationResponse details) {
        Product product = findOrCreateProduct(id);

        List<ProductDetail> productDetails = List.of(
                createProductDetail(product, OutlineType.MAIN, details.main()),
                createProductDetail(product, OutlineType.USAGE, details.usage()),
                createProductDetail(product, OutlineType.WARNING, details.warning()),
                createProductDetail(product, OutlineType.SPECS, details.specs()),
                createProductDetail(product, OutlineType.CERTIFICATION, details.certification())
        );
        product.addProductDetails(productDetails);
        productDetailRepository.saveAll(productDetails);
    }

    private ProductDetail createProductDetail(Product product, OutlineType outline, String content) {
        return ProductDetail.builder()
                .product(product)
                .outline(outline)
                .content(content)
                .build();
    }

    @Transactional
    public Product findOrCreateProduct(String productId) {
        return productRepository.findById(productId)
                .orElseGet(() -> productRepository.save(
                        Product.builder()
                                .id(productId)
                                .build()));
    }

    private String getContentByOutline(AllDetailExplanationResponse details, OutlineType outline) {
        return switch (outline) {
            case MAIN -> details.main();
            case USAGE -> details.usage();
            case WARNING -> details.warning();
            case SPECS -> details.specs();
            case CERTIFICATION -> details.certification();
        };
    }
}
