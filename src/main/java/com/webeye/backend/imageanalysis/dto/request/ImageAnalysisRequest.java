package com.webeye.backend.imageanalysis.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Schema(description = "이미지 분석 URL 요청")
@Builder
public record ImageAnalysisRequest (
        @NotEmpty(message = "이미지 URL 목록은 비어있을 수 없습니다.")
        List<String> urls
) {
}

