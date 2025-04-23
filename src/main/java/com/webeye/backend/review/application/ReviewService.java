package com.webeye.backend.review.application;

import com.webeye.backend.imageanalysis.infrastructure.OpenAiClient;
import com.webeye.backend.review.dto.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final OpenAiClient openAiClient;

    public ReviewResponse summarizeReview() {
        return null;
    }
}
