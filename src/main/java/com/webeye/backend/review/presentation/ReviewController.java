package com.webeye.backend.review.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.review.application.ReviewService;
import com.webeye.backend.review.dto.response.ReviewResponse;
import com.webeye.backend.review.presentation.swagger.ReviewSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.webeye.backend.global.dto.response.type.SuccessCode.REVIEW_SUMMARY_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/review/summary")
public class ReviewController implements ReviewSwagger {

    private final ReviewService reviewService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<ReviewResponse> summarizeReview() {
        return SuccessResponse.of(REVIEW_SUMMARY_SUCCESS, reviewService.summarizeReview());
    }
}
