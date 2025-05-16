package com.webeye.backend.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Schema(description = "상품 설명 이미지의 URL")
@Builder
public record ProductDetailAnalysisRequest(
        @Schema(description = "상품 이미지 URL")
        @NotEmpty(message = "이미지 URL 목록은 비어있을 수 없습니다.")
        List<String> urls
){
}
