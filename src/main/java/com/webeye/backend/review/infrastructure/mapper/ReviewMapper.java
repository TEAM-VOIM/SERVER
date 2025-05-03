package com.webeye.backend.review.infrastructure.mapper;

import com.webeye.backend.review.domain.Review;
import com.webeye.backend.review.dto.response.ReviewSummaryResponse;

import java.util.Arrays;

public class ReviewMapper {

    public static Review toEntity(ReviewSummaryResponse response) {
        return Review.builder()
                .positiveSummary(response.positive())
                .negativeSummary(response.negative())
                .keywords(String.join(",", response.keywords()))
                .build();
    }

    public static ReviewSummaryResponse toResponse(Review review) {
        return new ReviewSummaryResponse(
                review.getPositiveSummary(),
                review.getNegativeSummary(),
                Arrays.asList(review.getKeywords().split(","))
        );
    }
}
