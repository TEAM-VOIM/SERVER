package com.webeye.backend.explanation.presentation.swagger;

import com.webeye.backend.explanation.dto.request.ProductAnalysisRequest;
import com.webeye.backend.explanation.dto.request.ProductDetailAnalysisRequest;
import com.webeye.backend.explanation.dto.response.DetailExplanationResponse;
import com.webeye.backend.explanation.dto.response.PointExplanationResponse;
import com.webeye.backend.global.dto.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Explanation", description = "제품 설명 관련 API")
public interface ExplanationSwagger {
    @Operation(
            summary = "제품 설명 이미지에서 주요 요소 추출",
            description = "제품 설명 이미지를 입력받아 주요 설명 요소를 추출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "제품 주요 설명 요소가 추출되었습니다."
            )
    })
    SuccessResponse<PointExplanationResponse> productAnalysis(
            @RequestBody ProductAnalysisRequest request
    );

    @Operation(
            summary = "제품 주요 요소에 대한 상세 설명 추출",
            description = "제품 설명 이미지를 입력받아 주요 요소에 대한 상세 설명을 추출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "제품 주요 요소에 대한 상세 설명이 추출되었습니다."
            )
    })
    SuccessResponse<DetailExplanationResponse> productDetailAnalysis(
            @RequestBody ProductDetailAnalysisRequest request
    );
}

