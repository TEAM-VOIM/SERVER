package com.webeye.backend.product.dto.request;

import com.webeye.backend.allergy.type.AllergyType;
import com.webeye.backend.nutrition.domain.type.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.List;

@Schema(description = "사용자 정보 기반 식품 분석")
@Builder
public record FoodProductAnalysisRequest(
        @NotEmpty(message = "제품 ID는 비어있을 수 없습니다.")
        String productId,

        @NotBlank(message = "상품 제목은 비어있을 수 없습니다.")
        @Schema(description = "상품 제목")
        String title,

        @Schema(description = "상품 상세 정보 HTML")
        @NotEmpty(message = "상품 상세 정보의 HTML은 비어있을 수 없습니다.")
        @Pattern(regexp = ".*<img.*src=.*>.*", message = "HTML에는 최소한 하나의 이미지 태그가 포함되어야 합니다.")
        String html,

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

