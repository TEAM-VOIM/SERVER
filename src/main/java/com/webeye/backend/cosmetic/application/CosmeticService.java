package com.webeye.backend.cosmetic.application;

import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.explanation.dto.request.ProductAnalysisRequest;

public interface CosmeticService {
    CosmeticResponse analyzeCosmetic(ProductAnalysisRequest request);
}
