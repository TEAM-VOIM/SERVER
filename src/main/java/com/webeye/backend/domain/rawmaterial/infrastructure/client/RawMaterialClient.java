package com.webeye.backend.domain.rawmaterial.infrastructure.client;

import com.webeye.backend.domain.rawmaterial.dto.RawMaterialResponseDTO;
import com.webeye.backend.global.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "rawMaterialClient",
        url = "${open-api.url}",
        configuration = OpenFeignConfig.class
)
public interface RawMaterialClient {

    @GetMapping(
            value = "/openapi/tn_pubr_public_nutri_material_info_api",
            produces = "application/json"
    )
    RawMaterialResponseDTO getRawMaterialInfo(
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("numOfRows") int numOfRows,
            @RequestParam("type") String type
    );
}
