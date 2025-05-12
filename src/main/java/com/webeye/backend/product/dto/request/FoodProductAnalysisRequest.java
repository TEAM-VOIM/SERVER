package com.webeye.backend.product.dto.request;

import com.webeye.backend.allergy.type.AllergyType;
import com.webeye.backend.nutrition.domain.type.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Schema(description = "사용자 정보 기반 식품 분석")
@Builder
public record FoodProductAnalysisRequest(
        @NotEmpty(message = "제품 ID는 비어있을 수 없습니다.")
        String productId,

        @Schema(description = "상품 제목")
        String title,

        @Schema(description = "상품 이미지 URL")
        @NotEmpty(message = "이미지 URL 목록은 비어있을 수 없습니다.")
        List<String> urls,

        @Schema(description = "사용자 출생년도")
        @NotNull(message = "사용자의 출생연도는 비어있을 수 없습니다.")
        Integer birthYear,

        @Schema(description = "사용자 성별")
        @NotNull(message = "사용자의 성별은 비어있을 수 없습니다.")
        Gender gender,

        @Schema(description = "사용자 알레르기")
        List<AllergyType> allergies
) {
}

