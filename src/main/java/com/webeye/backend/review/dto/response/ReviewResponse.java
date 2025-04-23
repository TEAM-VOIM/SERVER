package com.webeye.backend.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "리뷰 요약")
public record ReviewResponse(
        @Schema(description = "별점", example = "4.5")
        Double score,
        @Schema(description = "긍정 리뷰", example = "맛있다는 평가가 많습니다.")
        String positive,
        @Schema(description = "부정 리뷰", example = "배송이 느리다는 평가가 많습니다.")
        String negative,
        @Schema(description = "키워드", example = "맛있어요, 신선해요")
        List<String> keywords
) {
}