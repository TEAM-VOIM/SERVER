package com.webeye.backend.allergy.application;

import com.webeye.backend.allergy.dto.response.AllergyResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllergyService {
    private final OpenAiClient openAiClient;

    public AllergyResponse analyzeAllergy(ImageAnalysisRequest request) {
        return openAiClient.explainAllergy(request);
    }

}
