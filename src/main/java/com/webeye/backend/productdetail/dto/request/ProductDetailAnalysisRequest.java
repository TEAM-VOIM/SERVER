package com.webeye.backend.productdetail.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Schema(description = "상품 설명 요청")
@Builder
public record ProductDetailAnalysisRequest(
        @Schema(description = "상품 ID")
        @NotBlank(message = "상품 ID는 비어있을 수 없습니다.")
        String productId,

        @Schema(description = "상품 상세 정보 HTML")
        @NotEmpty(message = "상품 상세 정보의 HTML은 비어있을 수 없습니다.")
        @Pattern(regexp = ".*<img.*src=.*>.*", message = "HTML에는 최소한 하나의 이미지 태그가 포함되어야 합니다.")
        String html
){
}
