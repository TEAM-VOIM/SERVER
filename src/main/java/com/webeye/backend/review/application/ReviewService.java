package com.webeye.backend.review.application;

import com.webeye.backend.review.dto.request.ReviewSummaryRequest;
import com.webeye.backend.review.dto.response.ReviewSummaryResponse;
import com.webeye.backend.review.infrastructure.clovaX.ClovaXClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ClovaXClientService clovaXClientService;

    public ReviewSummaryResponse summarizeReview(ReviewSummaryRequest request) {
        String reviewText = String.join("\n", request.reviews());
        return clovaXClientService.summarizeReviewText(reviewText);
    }
}
