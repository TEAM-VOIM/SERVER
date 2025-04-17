package com.webeye.backend.nutrition.presentation.swagger;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.nutrition.dto.response.NutritionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Nutrition", description = "제품 영양소 관련 API")
public interface NutritionSwagger {
    @Operation(
            summary = "제품의 영양소 함량 추출",
            description = "음식 제품에 대한 영양소 함량을 추출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "영양소 함량이 성공적으로 추출되었습니다."
            )
    })
    SuccessResponse<NutritionResponse> nutritionAnalysis(
            @RequestBody ImageAnalysisRequest request
    );
}
