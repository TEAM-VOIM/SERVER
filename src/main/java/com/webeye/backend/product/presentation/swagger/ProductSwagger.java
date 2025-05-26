package com.webeye.backend.product.presentation.swagger;

import com.webeye.backend.product.dto.response.DetailExplanationResponse;
import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.productdetail.domain.type.OutlineType;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.product.dto.request.ProductDetailAnalysisRequest;
import com.webeye.backend.product.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "[제품]", description = "제품 관련 API")
public interface ProductSwagger {
    @Operation(
            summary = "음식 제품 분석",
            description = "음식 제품을 분석합니다. 질환, 알레르기를 입력받아 이를 유발하는 성분과 영양소 권장량을 초과하는 성분이 포함되어 있는지를 반환합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "음식 제품이 성공적으로 분석되었습니다."
            )
    })
    SuccessResponse<ProductResponse> foodAnalysis(
            @RequestBody FoodProductAnalysisRequest request
    );
}
