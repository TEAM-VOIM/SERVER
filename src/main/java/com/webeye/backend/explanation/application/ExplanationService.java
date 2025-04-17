package com.webeye.backend.explanation.application;

import com.webeye.backend.explanation.dto.response.DetailExplanationResponse;
import com.webeye.backend.explanation.dto.response.PointExplanationResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.imageanalysis.infrastrucutre.OpenAiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExplanationService {
    private final OpenAiClient openAiClient;

    public PointExplanationResponse analyzeProductPoint(ImageAnalysisRequest request) {
        return openAiClient.explainProductPoint(request);
    }

    public DetailExplanationResponse analyzeProductDetail(ImageAnalysisRequest request) {
        return openAiClient.explainProductDetail(request);
    }
}
