package com.webeye.backend.review.infrastructure.clovaX.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ContentType {
    TEXT("text"),
    IMAGE_URL("image_url");

    private final String type;

    ContentType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
