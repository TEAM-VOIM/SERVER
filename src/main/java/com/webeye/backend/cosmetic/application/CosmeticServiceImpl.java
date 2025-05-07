package com.webeye.backend.cosmetic.application;

import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CosmeticServiceImpl implements CosmeticService {

    private final OpenAiClient openAiClient;

    @Override
    public CosmeticResponse analyzeCosmetic(ProductAnalysisRequest request) {
        return openAiClient.explainCosmetic(request);
    }
}
