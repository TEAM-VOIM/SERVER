package com.webeye.backend.productdetail.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.productdetail.dto.request.ProductDetailAnalysisRequest;
import com.webeye.backend.productdetail.dto.response.DetailExplanationResponse;
import com.webeye.backend.productdetail.application.ProductDetailService;
import com.webeye.backend.productdetail.domain.type.OutlineType;
import com.webeye.backend.productdetail.presentation.swagger.ProductDetailSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.PRODUCT_DETAIL_EXPLANATION_ANALYSIS_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-detail")
public class ProductDetailController implements ProductDetailSwagger {
    private final ProductDetailService productDetailService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{outline}")
    public SuccessResponse<DetailExplanationResponse> productDetailAnalysis(
            @PathVariable OutlineType outline, @Valid @RequestBody ProductDetailAnalysisRequest request) {
        return SuccessResponse.of(PRODUCT_DETAIL_EXPLANATION_ANALYSIS_SUCCESS,
                productDetailService.analyzeProductDetail(outline, request));
    }
}
