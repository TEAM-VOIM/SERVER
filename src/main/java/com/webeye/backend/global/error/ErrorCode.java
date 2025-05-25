package com.webeye.backend.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // open ai
    OPEN_AI_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "OpenAI의 응답을 받지 못했습니다."),
    OPEN_AI_REFUSAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "OpenAI에서 형식에 맞지 않는 응답이 반환되었습니다."),
    FILE_EXTENSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "URL에서 확장자를 찾을 수 없습니다."),
    UNSUPPORTED_IMAGE_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 이미지 형식입니다."),
    INVALID_IMAGE_URL(HttpStatus.BAD_REQUEST, "잘못된 이미지 URL입니다."),

    // image url extract
    IMAGE_URL_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지의 URL이 추출되지 않았습니다."),

    // open api
    OPEN_API_RESPONSE_NULL(HttpStatus.INTERNAL_SERVER_ERROR, "Open API 응답에 실패했습니다."),
    OPEN_API_DATA_MISSING(HttpStatus.NOT_FOUND, "Open API 데이터가 존재하지 않습니다."),

    // product
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 제품입니다."),

    // nutrient
    NUTRIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 영양성분입니다."),

    // cosmetic
    COSMETIC_INGREDIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 화장품 성분입니다.")
    ;
    private final HttpStatus status;
    private final String errorMessage;
}
