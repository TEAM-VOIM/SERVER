package com.webeye.backend.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(description = "리뷰 만족도")
public record ReviewSummaryRequest(
        @Schema(description = "쿠팡 상품 ID", example = "85241789")
        String productId,

        @Schema(description = "리뷰 만족도 통계",
                example = """
                {
                  "맛 만족도": {
                    "맛있어요": 55,
                    "보통이에요": 16,
                    "생각보다 별로예요": 29
                  },
                  "당도": {
                    "아주 달콤해요": 39,
                    "적당해요": 29,
                    "달지 않아요": 32
                  },
                  "별점": {
                    "최고": 83,
                    "좋음": 11,
                    "보통": 4,
                    "별로": 1,
                    "나쁨": 1
                   }
                }
                """)
        Map<String, Map<String, Integer>> reviews
) {
}
