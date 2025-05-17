package com.webeye.backend.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Schema(description = "상품 설명 이미지의 HTML")
@Builder
public record ProductDetailAnalysisRequest(
        @Schema(description = "상품 상세 이미지")
        @NotEmpty(message = "이미지 URL 목록은 비어있을 수 없습니다.")
        String html
){
}
