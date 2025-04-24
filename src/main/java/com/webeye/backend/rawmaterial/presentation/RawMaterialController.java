package com.webeye.backend.rawmaterial.presentation;

import com.webeye.backend.rawmaterial.application.service.RawMaterialService;
import com.webeye.backend.rawmaterial.dto.RawMaterialResponse;
import com.webeye.backend.rawmaterial.presentation.swagger.RawMaterialSwagger;
import com.webeye.backend.global.dto.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.RAW_MATERIAL_API_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/raw-materials")
public class RawMaterialController implements RawMaterialSwagger {

    private final RawMaterialService rawMaterialService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public SuccessResponse<RawMaterialResponse.Body> callRawMaterialApi(
            @RequestParam Integer pageNo,
            @RequestParam Integer numOfRows
    ) {
        return SuccessResponse.of(
                RAW_MATERIAL_API_SUCCESS,
                rawMaterialService.callRawMaterialAPI(pageNo, numOfRows)
        );
    }
}
