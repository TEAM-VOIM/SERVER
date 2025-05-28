package com.webeye.backend.productdetail.presentation.swagger;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.productdetail.dto.request.ProductDetailAnalysisRequest;
import com.webeye.backend.productdetail.dto.response.DetailExplanationResponse;
import com.webeye.backend.productdetail.domain.type.OutlineType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "[제품 상세 설명]", description = "제품 상세 설명 관련 API")
public interface ProductDetailSwagger {
    @Operation(
            summary = "제품 주요 개요에 대한 상세 설명 추출",
            description = "제품 ID와 상세 설명 이미지가 포함된 HTML, 개요를 입력받아 주요 요소에 대한 상세 설명을 추출합니다. " +
                    "MAIN: 주요정보, USAGE: 사용정보, WARNING: 주의 및 보관, SPECS: 규격 및 옵션"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "제품 주요 요소에 대한 상세 설명이 추출되었습니다."
            )
    })
    SuccessResponse<DetailExplanationResponse> productDetailAnalysis(
            @Parameter(in = ParameterIn.PATH, description = "상품 개요", required = true)
            OutlineType outline,
            @RequestBody ProductDetailAnalysisRequest request
    );
}
