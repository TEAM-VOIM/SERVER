package com.webeye.backend.imageanalysis.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "이미지 분석 응답")
@Builder
public record ImageAnalysisResponse(
        String analysis
) {
}
