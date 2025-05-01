package com.webeye.backend.review.presentation.swagger;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.review.dto.request.ReviewSummaryRequest;
import com.webeye.backend.review.dto.response.ReviewSummaryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "[리뷰 요약]", description = "리뷰 요약 및 분석 관련 API")
public interface ReviewSwagger {
    @Operation(
            summary = "제품의 리뷰 요약 및 분석",
            description = "해당 제품에 대한 전체적인 리뷰를 요약 및 분석합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "리뷰 요약 및 분석이 성공적으로 수행되었습니다."
            )
    })
    SuccessResponse<ReviewSummaryResponse> summarizeReview(@RequestBody @Valid ReviewSummaryRequest request);
}
