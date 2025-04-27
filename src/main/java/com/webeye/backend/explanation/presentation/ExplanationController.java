package com.webeye.backend.explanation.presentation;

import com.webeye.backend.explanation.application.ExplanationService;
import com.webeye.backend.explanation.dto.response.DetailExplanationResponse;
import com.webeye.backend.explanation.dto.response.PointExplanationResponse;
import com.webeye.backend.explanation.presentation.swagger.ExplanationSwagger;
import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.PRODUCT_DETAIL_EXPLANATION_ANALYSIS_SUCCESS;
import static com.webeye.backend.global.dto.response.type.SuccessCode.PRODUCT_POINT_EXPLANATION_ANALYSIS_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/explanation")
public class ExplanationController implements ExplanationSwagger {
    private final ExplanationService explanationService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "")
    public SuccessResponse<PointExplanationResponse> productAnalysis(@Valid @RequestBody ImageAnalysisRequest request) {
        return SuccessResponse.of(PRODUCT_POINT_EXPLANATION_ANALYSIS_SUCCESS, explanationService.analyzeProductPoint(request));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/detail")
    public SuccessResponse<DetailExplanationResponse> productDetailAnalysis(@Valid @RequestBody ImageAnalysisRequest request) {
        return SuccessResponse.of(PRODUCT_DETAIL_EXPLANATION_ANALYSIS_SUCCESS, explanationService.analyzeProductDetail(request));
    }
}
