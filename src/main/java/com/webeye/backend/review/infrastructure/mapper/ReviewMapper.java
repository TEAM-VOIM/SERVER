package com.webeye.backend.review.infrastructure.mapper;

import com.webeye.backend.review.domain.Review;
import com.webeye.backend.review.dto.response.ReviewSummaryResponse;

import java.util.Arrays;

public class ReviewMapper {

    public static Review toEntity(ReviewSummaryResponse response) {
        return Review.builder()
                .positiveSummary(String.join("||", response.positiveReviews()))
                .negativeSummary(String.join("||", response.negativeReviews()))
                .keywords(String.join(",", response.keywords()))
                .build();
    }

    public static ReviewSummaryResponse toResponse(Review review) {
        return new ReviewSummaryResponse(
                review.getAverageRating(),
                Arrays.asList(review.getPositiveSummary().split("\\|\\|")),
                Arrays.asList(review.getNegativeSummary().split("\\|\\|")),
                Arrays.asList(review.getKeywords().split(","))
        );
    }
}
