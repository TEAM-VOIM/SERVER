package com.webeye.backend.review.infrastructure.clovaX.dto.request;

import java.util.List;

public record ClovaXRequest(
        List<ClovaXMessage> messages
) {
}
