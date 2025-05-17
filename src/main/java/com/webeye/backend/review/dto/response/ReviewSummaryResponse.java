package com.webeye.backend.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "리뷰 요약")
public record ReviewSummaryResponse(
        @Schema(description = "평균 별점", example = "4.85")
        double averageRating,
        @Schema(description = "긍정 리뷰", example = "맛있다는 평가가 많습니다.")
        String positive,
        @Schema(description = "부정 리뷰", example = "배송이 느리다는 평가가 많습니다.")
        String negative,
        @Schema(description = "키워드", example = "맛있어요, 신선해요")
        List<String> keywords
) {
}