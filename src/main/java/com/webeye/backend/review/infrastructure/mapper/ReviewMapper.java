package com.webeye.backend.review.infrastructure.mapper;

import com.webeye.backend.product.domain.Product;
import com.webeye.backend.review.domain.Review;
import com.webeye.backend.review.dto.response.ReviewSummaryResponse;

import java.util.Arrays;
import java.util.List;

public class ReviewMapper {

    public static Review toEntity(ReviewSummaryResponse response, Product product) {
        Review review = Review.builder()
                .averageRating(response.averageRating())
                .positiveSummary(
                        response.positiveReviews() == null || response.positiveReviews().isEmpty()
                                ? "리뷰가 없습니다."
                                : String.join("||", response.positiveReviews())
                )
                .negativeSummary(
                        response.negativeReviews() == null || response.negativeReviews().isEmpty()
                                ? "리뷰가 없습니다."
                                : String.join("||", response.negativeReviews())
                )
                .keywords(
                        response.keywords() == null || response.keywords().isEmpty()
                                ? ""
                                : String.join(",", response.keywords())
                )
                .build();
        review.associateWithProduct(product);

        return review;
    }

    public static ReviewSummaryResponse toResponse(Review review, int totalCount) {
        return new ReviewSummaryResponse(
                totalCount,
                review.getAverageRating(),
                Arrays.asList(review.getPositiveSummary().split("\\|\\|")),
                Arrays.asList(review.getNegativeSummary().split("\\|\\|")),
                Arrays.asList(review.getKeywords().split(","))
        );
    }

    public static ReviewSummaryResponse toNullResponse(int totalCount, double averageRating) {
        return new ReviewSummaryResponse(
                totalCount,
                averageRating,
                List.of("리뷰가 존재하지 않습니다."),
                List.of("리뷰가 존재하지 않습니다."),
                List.of("리뷰가 존재하지 않습니다.")
        );
    }
}
