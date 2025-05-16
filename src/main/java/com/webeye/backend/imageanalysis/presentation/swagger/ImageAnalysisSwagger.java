package com.webeye.backend.imageanalysis.presentation.swagger;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.imageanalysis.dto.response.ImageAnalysisResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "이미지 분석", description = "이미지 분석 관련 API")
public interface ImageAnalysisSwagger {
    @Operation(
            summary = "[이미지 분석]",
            description = "이미지 URL을 입력받아 이미지를 분석을 제공합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "이미지가 성공적으로 분석되었습니다."
            )
    })
    SuccessResponse<ImageAnalysisResponse> imageAnalysis(
            @RequestBody ImageAnalysisRequest request
    );
}
