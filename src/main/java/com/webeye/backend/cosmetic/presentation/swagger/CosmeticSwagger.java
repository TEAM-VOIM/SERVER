package com.webeye.backend.cosmetic.presentation.swagger;

import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "[화장품 주의 성분]", description = "화장품 주의 성분 관련 API")
public interface CosmeticSwagger {
    @Operation(
            summary = "화장품 주의 원료 성분 추출",
            description = "해당 화장품에 대한 주의 원료 성분을 추출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "화장품 주의 성분이 성공적으로 추출되었습니다."
            )
    })
    SuccessResponse<CosmeticResponse> analyzeCosmetic(
            @RequestBody ProductAnalysisRequest request
    );
}
