package com.webeye.backend.healthfood.infrastructure.client;

import com.webeye.backend.global.config.OpenFeignConfig;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "healthFoodClient",
        url = "${open-api.health-food.url}",
        configuration = OpenFeignConfig.class
)
public interface HealthFoodClient {

    @GetMapping("/{keyId}/{serviceId}/{dataType}/{startIdx}/{endIdx}")
    HealthFoodResponse getHealthFood(
            @PathVariable("keyId") String keyId,
            @PathVariable("serviceId") String serviceId,
            @PathVariable("dataType") String dataType,
            @PathVariable("startIdx") String startIdx,
            @PathVariable("endIdx") String endIdx
    );
}
