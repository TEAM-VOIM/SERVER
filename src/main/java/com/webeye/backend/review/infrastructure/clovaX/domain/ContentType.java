package com.webeye.backend.review.infrastructure.clovaX.domain;

import lombok.Getter;

@Getter
public enum ContentType {
    TEXT("text"),
    IMAGE_URL("image_url");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }
}
