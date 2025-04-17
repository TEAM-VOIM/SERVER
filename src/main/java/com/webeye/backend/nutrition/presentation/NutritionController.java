package com.webeye.backend.nutrition.presentation;

import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.nutrition.application.NutritionService;
import com.webeye.backend.nutrition.dto.response.NutritionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/nutrition")
public class NutritionController {
    private final NutritionService nutritionService;

    @PostMapping(value = "")
    public NutritionResponse nutritionAnalysis(@RequestBody ImageAnalysisRequest request) {
        return nutritionService.analyzeNutrition(request);
    }
}
