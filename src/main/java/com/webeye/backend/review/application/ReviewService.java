package com.webeye.backend.review.application;

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
    public final ReviewRepository reviewRepository;

    @Transactional
    public ReviewSummaryResponse summarizeReview(ReviewSummaryRequest request) {
        String reviewText = String.join("\n", request.reviews());

        ReviewSummaryResponse response = clovaXClientService.summarizeReviewText(reviewText);

        Review review = ReviewMapper.toEntity(response);
        reviewRepository.save(review);

        return response;
    }
}
