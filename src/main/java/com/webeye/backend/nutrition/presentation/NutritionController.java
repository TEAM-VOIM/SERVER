package com.webeye.backend.nutrition.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.nutrition.application.NutritionService;
import com.webeye.backend.nutrition.dto.response.NutritionResponse;
import com.webeye.backend.nutrition.presentation.swagger.NutritionSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.NUTRITION_ANALYSIS_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/nutrition")
public class NutritionController implements NutritionSwagger {
    private final NutritionService nutritionService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "")
    public SuccessResponse<NutritionResponse> nutritionAnalysis(@Valid @RequestBody ImageAnalysisRequest request) {
        return SuccessResponse.of(NUTRITION_ANALYSIS_SUCCESS, nutritionService.analyzeNutrition(request));
    }
}
