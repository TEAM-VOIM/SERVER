package com.webeye.backend.rawmaterial.presentation;

import com.webeye.backend.rawmaterial.application.service.RawMaterialService;
import com.webeye.backend.rawmaterial.dto.RawMaterialResponse;
import com.webeye.backend.rawmaterial.presentation.swagger.RawMaterialSwagger;
import com.webeye.backend.global.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.webeye.backend.global.dto.response.type.SuccessCode.RAW_MATERIAL_API_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/raw-materials")
public class RawMaterialController implements RawMaterialSwagger {

    private final RawMaterialService rawMaterialService;

    @GetMapping
    public SuccessResponse<RawMaterialResponse.Body> callRawMaterialApi(int pageNo, int numOfRows) {
        return SuccessResponse.of(RAW_MATERIAL_API_SUCCESS, rawMaterialService.callRawMaterialAPI(pageNo, numOfRows));
    }
}
