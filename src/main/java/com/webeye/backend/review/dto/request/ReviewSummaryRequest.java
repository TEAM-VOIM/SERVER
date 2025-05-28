package com.webeye.backend.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "리뷰 만족도")
public record ReviewSummaryRequest(
        @Schema(description = "쿠팡 상품 ID", example = "85241789")
        String productId,

        @Schema(description = "별점 통계")
        ReviewRating reviewRating,

        @Schema(description = "쿠팡 리뷰 목록", example = """
                [맛있어요, 배송 느려요, 부드러워요]
                """)
        List<String> reviews
) {
        public record ReviewRating(
                @Schema(description = "총 별점 수", example = "150")
                int totalCount,

                @Schema(description = "별점 등급별 수", example = """
                    [83, 11, 4, 1, 1]
                    """)
                List<Integer> ratings
        ){}
}
