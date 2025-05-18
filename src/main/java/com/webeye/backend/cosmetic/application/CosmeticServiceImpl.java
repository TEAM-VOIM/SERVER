package com.webeye.backend.cosmetic.application;

import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.imageanalysis.infrastructure.ImageUrlExtractor;
import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CosmeticServiceImpl implements CosmeticService {

    private final OpenAiClient openAiClient;
    private final ImageUrlExtractor imageUrlExtractor;

    @Override
    public CosmeticResponse analyzeCosmetic(ProductAnalysisRequest request) {
        return openAiClient.explainCosmetic(imageUrlExtractor.extractImageUrlFromHtml(request.html()));
    }
}
