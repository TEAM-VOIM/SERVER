package com.webeye.backend.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    OPEN_API_RESPONSE_NULL(HttpStatus.INTERNAL_SERVER_ERROR, "Open API 응답에 실패했습니다."),
    OPEN_API_DATA_MISSING(HttpStatus.NOT_FOUND, "Open API 데이터가 존재하지 않습니다.")
    ;
    private final HttpStatus status;
    private final String errorMessage;
}
