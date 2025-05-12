package com.webeye.backend.explanation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Schema(description = "제품 상세 분석")
@Builder
public record ProductDetailAnalysisRequest(
        @NotEmpty(message = "제품 ID는 비어있을 수 없습니다.")
        String productId,

        @NotEmpty(message = "제품 설명 요소는 비어있을 수 없습니다.")
        String description,

        @Schema(description = "상품 이미지 URL")
        @NotEmpty(message = "이미지 URL 목록은 비어있을 수 없습니다.")
        List<String> urls
) {
}
