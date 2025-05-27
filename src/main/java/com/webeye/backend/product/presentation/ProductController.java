package com.webeye.backend.product.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.product.application.ProductService;
import com.webeye.backend.product.dto.response.ProductResponse;
import com.webeye.backend.product.presentation.swagger.ProductSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.FOOD_PRODUCT_ANALYSIS_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/products")
public class ProductController implements ProductSwagger {
    private final ProductService productService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/foods")
    public SuccessResponse<ProductResponse> foodAnalysis(@Valid @RequestBody FoodProductAnalysisRequest request) {
        return SuccessResponse.of(FOOD_PRODUCT_ANALYSIS_SUCCESS, productService.analyzeFoodProduct(request));
    }
}
