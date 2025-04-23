package com.webeye.backend.review.infrastructure.clovaX.domain;

import lombok.Getter;

@Getter
public enum Role {
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant");

    private final String value;

    Role(String role) {
        this.value = role;
    }
}
