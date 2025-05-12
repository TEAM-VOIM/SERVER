package com.webeye.backend.healthfood.presentation.swagger;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.healthfood.dto.HealthFoodAiResponse;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Operation(
            summary = "건강 기능 식품 효능 키워드 분석",
            description = "해당 건강 기능 식품의 효능을 분석하고 키워드를 표출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "건강 기능 식품 효능 키워드 분석이 성공적으로 실행되었습니다."
            )
    })
    SuccessResponse<HealthFoodAiResponse> analyzeHealthFood(
            @Valid @RequestBody FoodProductAnalysisRequest request
    );
}
