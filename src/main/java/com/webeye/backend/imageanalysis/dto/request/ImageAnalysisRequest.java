package com.webeye.backend.imageanalysis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Schema(description = "이미지 분석")
public record ImageAnalysisRequest(
        @Schema(description = "상품 이미지 URL")
        @NotEmpty(message = "이미지 URL 목록은 비어있을 수 없습니다.")
        @URL
        String url
) {
}
