package com.webeye.backend.review.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.review.application.ReviewService;
import com.webeye.backend.review.dto.request.ReviewSummaryRequest;
import com.webeye.backend.review.dto.response.ReviewResponse;
import com.webeye.backend.review.presentation.swagger.ReviewSwagger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.REVIEW_SUMMARY_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/review")
public class ReviewController implements ReviewSwagger {

    private final ReviewService reviewService;

    @Override
    @PostMapping("/summary")
    @ResponseStatus(HttpStatus.OK)
    public SuccessResponse<ReviewResponse> summarizeReview(@RequestBody @Valid ReviewSummaryRequest request) {
        return SuccessResponse.of(REVIEW_SUMMARY_SUCCESS, reviewService.summarizeReview(request));
    }
}
