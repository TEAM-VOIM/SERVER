package com.webeye.backend.review.infrastructure.clovaX.dto.request;

import com.webeye.backend.review.infrastructure.clovaX.model.Role;

import java.util.List;

public record ClovaXMessage(
        Role role,
        List<ClovaXContent> content
) {
}
