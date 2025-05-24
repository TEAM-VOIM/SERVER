package com.webeye.backend.review.application;

import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.persistent.ProductRepository;
import com.webeye.backend.review.domain.Review;
import com.webeye.backend.review.dto.request.ReviewSummaryRequest;
import com.webeye.backend.review.dto.response.ReviewSummaryResponse;
import com.webeye.backend.review.infrastructure.clovaX.ClovaXClientService;
import com.webeye.backend.review.infrastructure.mapper.ReviewMapper;
import com.webeye.backend.review.infrastructure.persistence.ReviewRepository;
import com.webeye.backend.review.infrastructure.util.ReviewCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ClovaXClientService clovaXClientService;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ReviewSummaryResponse summarizeReview(ReviewSummaryRequest request) {
        Product product = findOrCreateProduct(request.productId());

        // DB에 존재할 경우, DB에서 반환
        Review existingReview = product.getReview();
        if (existingReview != null) {
            return ReviewMapper.toResponse(existingReview, request.reviewRating().totalCount());
        }
        Map<String, Integer> ratingMap = ReviewCalculator.convertToRatingMap(request.reviewRating().ratings());

        // 리뷰 내 별점만 존재하고 리뷰 내용은 존재하지 않을 경우
        if (request.reviews() == null || request.reviews().isEmpty()) {
            double average = ReviewCalculator.calculateAverageRating(ratingMap, request.reviewRating().totalCount());
            ReviewSummaryResponse nullResponse = ReviewMapper.toNullResponse(request.reviewRating().totalCount(), average);

            Review review = ReviewMapper.toEntity(nullResponse, product);
            reviewRepository.save(review);

            return ReviewMapper.toResponse(review, request.reviewRating().totalCount());
        }

        ReviewSummaryResponse response = clovaXClientService.summarizeReviewText(
                request.reviews(),
                ratingMap,
                request.reviewRating().totalCount()
        );
        Review review = ReviewMapper.toEntity(response, product);

        reviewRepository.save(review);

        return response;
    }

    @Transactional
    public Product findOrCreateProduct(String productId) {
        return productRepository.findByIdWithReview(productId)
                .orElseGet(() -> productRepository.save(
                        Product.builder()
                                .id(productId)
                                .build()));
    }
}
