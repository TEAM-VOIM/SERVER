package com.webeye.backend.allergy.presentation.swagger;

import com.webeye.backend.allergy.dto.response.AllergyResponse;
import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.nutrition.dto.response.NutritionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Allergy", description = "제품 알러지 유발 성분 관련 API")
public interface AllergySwagger {
    @Operation(
            summary = "제품의 알러지 유발 성분 추출",
            description = "음식 제품에 대한 알러지 유발 성분을 추출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "알러지 유발 요소가 성공적으로 추출되었습니다."
            )
    })
    SuccessResponse<AllergyResponse> allergyAnalysis(
            @RequestBody ImageAnalysisRequest request
    );

}
