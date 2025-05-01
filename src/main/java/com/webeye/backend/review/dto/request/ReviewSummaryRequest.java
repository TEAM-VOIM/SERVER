package com.webeye.backend.review.dto.request;

import java.util.List;

public record ReviewSummaryRequest(
        List<String> reviews
) {
}
