package com.webeye.backend.review.infrastructure.clovaX;

import com.webeye.backend.global.config.OpenFeignConfig;
import com.webeye.backend.review.dto.response.ReviewResponse;
import com.webeye.backend.review.infrastructure.clovaX.dto.request.ClovaXRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "clovaXClient",
        url = "${clova.url}",
        configuration = OpenFeignConfig.class
)
public interface ClovaXClient {

    @PostMapping()
    ReviewResponse createReviewSummary(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-NCP-CLOVASTUDIO-REQUEST-ID") String requestId,
            @RequestHeader("Content-Type") String contentType,
            @RequestBody @Valid ClovaXRequest request
    );
}
