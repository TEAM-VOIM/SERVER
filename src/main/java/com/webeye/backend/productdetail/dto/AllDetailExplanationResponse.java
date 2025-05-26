package com.webeye.backend.productdetail.dto;

import lombok.Builder;

@Builder
public record AllDetailExplanationResponse(
        String main,
        String usage,
        String warning,
        String specs,
        String certification
) {
}
