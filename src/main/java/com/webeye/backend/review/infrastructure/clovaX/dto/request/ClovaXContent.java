package com.webeye.backend.review.infrastructure.clovaX.dto.request;

import com.webeye.backend.review.infrastructure.clovaX.domain.ContentType;

public record ClovaXContent(
        ContentType type,
        String text
) {
}
