package com.webeye.backend.product.dto.request;

import com.webeye.backend.allergy.type.AllergyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Schema(description = "이미지 분석 URL 요청")
@Builder
public record ProductAnalysisRequest(
        @NotEmpty(message = "제품 ID는 비어있을 수 없습니다.")
        String productId,

        @NotEmpty(message = "이미지 URL 목록은 비어있을 수 없습니다.")
        List<String> urls,

        @Schema(description = "사용자 알레르기")
        List<AllergyType> allergies,

        @Schema(description = "사용자 질병")
        List<String> diseases
) {
}

