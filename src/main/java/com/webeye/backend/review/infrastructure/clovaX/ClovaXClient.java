package com.webeye.backend.review.infrastructure.clovaX;

import com.webeye.backend.global.config.OpenFeignConfig;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXRequest;
import com.webeye.backend.review.infrastructure.clovaX.dto.response.ClovaXResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "clovaXClient",
        url = "${clova.url}",
        configuration = OpenFeignConfig.class
)
public interface ClovaXClient {

    @PostMapping
    ClovaXResponse createReviewSummary(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-NCP-CLOVASTUDIO-REQUEST-ID") String requestId,
            @RequestBody @Valid ClovaXRequest request
    );
}
