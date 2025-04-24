package com.webeye.backend.rawmaterial.presentation.swagger;

import com.webeye.backend.rawmaterial.dto.RawMaterialResponse;
import com.webeye.backend.global.dto.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "[원재료 주의 영양 성분]", description = "원재료 주의 영양 성분 표시 관련 API")
public interface RawMaterialSwagger {
    @Operation(
            summary = "원재료 영양 성분 OPEN API 호출",
            description = "공공 데이터 포털 원재료 영양 성분 OPEN API를 호출합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OPEN API가 성공적으로 호출되었습니다."
            )
    })
    SuccessResponse<RawMaterialResponse.Body> callRawMaterialApi(
            @RequestParam Integer pageNo,
            @RequestParam Integer numOfRows
    );
}
