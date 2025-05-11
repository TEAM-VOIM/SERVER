package com.webeye.backend.healthfood.presentation.swagger;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[건강 기능 식품]", description = "건강 기능 식품 관련 API")
public interface HealthFoodSwagger {
    @Operation(
            summary = "건강 기능 식품 OPEN API 호출",
            description = "식품 안전 나라 건강 기능 식품 품목 분류 정보 OPEN API를 호출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OPEN API가 성공적으로 호출되었습니다."
            )
    })
    SuccessResponse<HealthFoodResponse.I2710> callHealthFoodApi();
}
