package com.webeye.backend.review.application;

import com.webeye.backend.product.domain.Product;
import com.webeye.backend.product.persistent.ProductRepository;
import com.webeye.backend.review.domain.Review;
import com.webeye.backend.review.dto.request.ReviewSummaryRequest;
import com.webeye.backend.review.dto.response.ReviewSummaryResponse;
import com.webeye.backend.review.infrastructure.clovaX.ClovaXClientService;
import com.webeye.backend.review.infrastructure.mapper.ReviewMapper;
import com.webeye.backend.review.infrastructure.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ClovaXClientService clovaXClientService;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ReviewSummaryResponse summarizeReview(ReviewSummaryRequest request) {
        Product product = findOrCreateProduct(request.productId());

        Review existingReview = product.getReview();
        if (existingReview != null) {
            return ReviewMapper.toResponse(existingReview);
        }

        ReviewSummaryResponse response = clovaXClientService.summarizeReviewText(
                request.reviews(),
                request.reviewRating().ratings(),
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
