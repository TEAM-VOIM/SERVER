package com.webeye.backend.rawmaterial.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "원재료 식품 AI 응답")
@Builder
public record RawMaterialAiResponse(
        @Schema(description = "원재료 식품 이름")
        String name
) {
}
