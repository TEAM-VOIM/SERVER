package com.webeye.backend.imageanalysis.application;

import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.imageanalysis.dto.response.ImageAnalysisResponse;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageAnalysisService {
    private final OpenAiClient openAiClient;

    public ImageAnalysisResponse analyzeImage(ImageAnalysisRequest request) {
        return openAiClient.explainImage(request);
    }

}
