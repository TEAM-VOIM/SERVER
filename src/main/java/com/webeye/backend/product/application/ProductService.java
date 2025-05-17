package com.webeye.backend.product.application;

import com.webeye.backend.allergy.application.AllergyService;
import com.webeye.backend.allergy.type.AllergyType;
import com.webeye.backend.product.dto.response.DetailExplanationResponse;
import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.nutrition.application.NutrientRecommendationService;
import com.webeye.backend.nutrition.dto.request.NutrientRecommendationRequest;
import com.webeye.backend.nutrition.dto.response.NutrientRecommendationResponse;
import com.webeye.backend.product.domain.ProductAllergy;
import com.webeye.backend.product.domain.type.OutlineType;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.nutrition.application.NutritionService;
import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.dto.request.ProductDetailAnalysisRequest;
import com.webeye.backend.product.dto.response.ProductResponse;
import com.webeye.backend.product.persistent.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final NutritionService nutritionService;
    private final NutrientRecommendationService nutrientRecommendationService;
    private final AllergyService allergyService;
    private final OpenAiClient openAiClient;

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse analyzeFoodProduct(FoodProductAnalysisRequest request) {
        if (productRepository.existsById(request.productId())) {
            Product product = productRepository.findById(request.productId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

            return ProductResponse.builder()
                    .allergyTypes(getAllergyResponse(product, request.allergies()))
                    .overRecommendationNutrients(getNutrientRecommendationResponse(request, product))
                    .build();
        }
        Product product = Product.builder()
                .id(request.productId())
                .build();
        productRepository.save(product);

        nutritionService.saveProductNutrition(product, request);
        allergyService.saveProductAllergy(product, request);

        return ProductResponse.builder()
                .allergyTypes(getAllergyResponse(product, request.allergies()))
                .overRecommendationNutrients(getNutrientRecommendationResponse(request, product))
                .build();
    }

    private List<AllergyType> getAllergyResponse(Product product, List<AllergyType> userAllergies) {
        return product.getAllergies().stream()
                .map(ProductAllergy::getAllergy)
                .distinct()
                .filter(userAllergies::contains)
                .toList();
    }

    private List<NutrientRecommendationResponse> getNutrientRecommendationResponse(FoodProductAnalysisRequest request, Product product) {
        return nutrientRecommendationService.analyzeNutrientSufficiency(NutrientRecommendationRequest
                .builder().birthYear(request.birthYear()).gender(request.gender()).product(product).build());
    }

    public DetailExplanationResponse analyzeProductDetail(OutlineType outline, ProductDetailAnalysisRequest request) {
        return openAiClient.explainProductDetail(outline, extractImageUrlFromHtml(request.html()));
    }

    private List<String> extractImageUrlFromHtml(String html) {
        Pattern pattern = Pattern.compile("<img[^>]+src=[\"'](//[^\"']+)[\"']");
        Matcher matcher = pattern.matcher(html);

        List<String> imageUrls = new ArrayList<>();

        while (matcher.find()) {
            String rawUrl = matcher.group(1);
            String fullUrl = "https:" + rawUrl;
            imageUrls.add(fullUrl);
        }
        return imageUrls;
    }
}
