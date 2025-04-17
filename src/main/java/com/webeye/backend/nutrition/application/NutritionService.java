package com.webeye.backend.nutrition.application;

import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.imageanalysis.infrastrucutre.OpenAiClient;
import com.webeye.backend.nutrition.dto.response.NutritionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NutritionService {
    private final OpenAiClient openAiClient;

    public NutritionResponse analyzeNutrition(ImageAnalysisRequest request) {
        return openAiClient.explainNutrition(request);
    }
}
