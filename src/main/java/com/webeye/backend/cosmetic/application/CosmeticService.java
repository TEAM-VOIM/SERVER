package com.webeye.backend.cosmetic.application;

import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;

public interface CosmeticService {
    CosmeticResponse analyzeCosmetic(ImageAnalysisRequest request);
}
