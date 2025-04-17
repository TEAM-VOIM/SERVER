package com.webeye.backend.explanation.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PointExplanationResponse(
        List<String> keyFeatures
) {
}
