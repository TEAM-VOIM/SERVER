package com.webeye.backend.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "리뷰 목록")
public record ReviewSummaryRequest(
        @Schema(description = "쿠팡 리뷰 목록", example = "[맛있어요, 배송 느려요, 부드러워요]")
        List<String> reviews
) {
}
