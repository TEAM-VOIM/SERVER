package com.webeye.backend.productdetail.dto.response;

import lombok.Builder;

@Builder
public record AllDetailExplanationResponse(
        String main,
        String usage,
        String warning,
        String specs
) {
}
