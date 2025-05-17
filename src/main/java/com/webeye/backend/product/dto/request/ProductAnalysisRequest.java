package com.webeye.backend.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Schema(description = "제품 분석")
@Builder
public record ProductAnalysisRequest(
        @NotEmpty(message = "제품 ID는 비어있을 수 없습니다.")
        String productId,

        @Schema(description = "상품 상세 정보 HTML")
        @NotEmpty(message = "상품 상세 정보의 HTML은 비어있을 수 없습니다.")
        String html
) {
}