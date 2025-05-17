package com.webeye.backend.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Map;

@Builder
@Schema(description = "리뷰 목록")
public record ReviewSummaryRequest(
        Map<String, Map<String, Integer>> reviews
) {
}
