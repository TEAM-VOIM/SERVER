package com.webeye.backend.domain.rawmaterial.presentation;

import com.webeye.backend.domain.rawmaterial.application.service.RawMaterialService;
import com.webeye.backend.domain.rawmaterial.dto.RawMaterialResponseDTO;
import com.webeye.backend.domain.rawmaterial.presentation.swagger.RawMaterialSwagger;
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
    public SuccessResponse<RawMaterialResponseDTO.Body> callRawMaterialApi() {
        return SuccessResponse.of(RAW_MATERIAL_API_SUCCESS, rawMaterialService.callRawMaterialAPI());
    }
}
