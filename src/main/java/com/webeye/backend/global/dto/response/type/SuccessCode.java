package com.webeye.backend.global.dto.response.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // health
    HEALTH_CHECK_SUCCESS(200, "Health Check Success"),

    // raw material
    RAW_MATERIAL_API_SUCCESS(200, "Raw Material API Success"),
    ;

    private final int status;
    private final String message;
}
