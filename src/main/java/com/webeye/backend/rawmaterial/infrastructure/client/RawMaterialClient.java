package com.webeye.backend.rawmaterial.infrastructure.client;

import com.webeye.backend.rawmaterial.dto.RawMaterialResponse;
import com.webeye.backend.global.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "rawMaterialClient",
        url = "${open-api.raw-material.url}",
        configuration = OpenFeignConfig.class
)
public interface RawMaterialClient {

    @GetMapping(
            value = "${open-api.raw-material.value}",
            produces = "application/json"
    )
    RawMaterialResponse getRawMaterialInfo(
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("numOfRows") int numOfRows,
            @RequestParam("type") String type
    );
}
