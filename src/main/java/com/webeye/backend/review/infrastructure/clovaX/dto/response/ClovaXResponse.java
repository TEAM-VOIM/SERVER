package com.webeye.backend.review.infrastructure.clovaX.dto.response;

public record ClovaXResponse(
        Status status,
        Result result
) {
    public record Status(
            String code,
            String message
    ) {}

    public record Result(
            Message message
    ) {
        public record Message(
                String role,
                String content
        ) {}
    }
}
