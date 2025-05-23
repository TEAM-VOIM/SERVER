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

import java.util.LinkedHashMap;
import java.util.List;
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

        Review existingReview = product.getReview();
        if (existingReview != null) {
            return ReviewMapper.toResponse(existingReview, request.reviewRating().totalCount());
        }
        Map<String, Integer> ratingMap = convertToRatingMap(request.reviewRating().ratings());

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

    private Map<String, Integer> convertToRatingMap(List<Integer> ratingsList) {
        List<String> keys = List.of("최고", "좋음", "보통", "별로", "나쁨");
        Map<String, Integer> ratingMap = new LinkedHashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            ratingMap.put(keys.get(i), i < ratingsList.size() ? ratingsList.get(i) : 0);
        }
        return ratingMap;
    }
}
