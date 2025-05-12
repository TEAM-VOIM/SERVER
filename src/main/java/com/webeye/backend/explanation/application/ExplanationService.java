package com.webeye.backend.explanation.application;

import com.webeye.backend.explanation.dto.request.ProductAnalysisRequest;
import com.webeye.backend.explanation.dto.response.DetailExplanationResponse;
import com.webeye.backend.explanation.dto.response.PointExplanationResponse;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExplanationService {
    private final OpenAiClient openAiClient;

    public PointExplanationResponse analyzeProductPoint(ProductAnalysisRequest request) {
        return openAiClient.explainProductPoint(request);
    }

    public DetailExplanationResponse analyzeProductDetail(ProductAnalysisRequest request) {
        return openAiClient.explainProductDetail(request);
    }
}
