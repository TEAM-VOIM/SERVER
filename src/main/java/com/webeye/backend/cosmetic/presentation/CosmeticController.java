package com.webeye.backend.cosmetic.presentation;

import com.webeye.backend.cosmetic.application.CosmeticService;
import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.cosmetic.presentation.swagger.CosmeticSwagger;
import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.product.dto.request.ProductAnalysisRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.webeye.backend.global.dto.response.type.SuccessCode.COSMETIC_ANALYSIS_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cosmetic")
public class CosmeticController implements CosmeticSwagger {

    private final CosmeticService cosmeticService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public SuccessResponse<CosmeticResponse> analyzeCosmetic(@Valid @RequestBody ProductAnalysisRequest request) {
        return SuccessResponse.of(COSMETIC_ANALYSIS_SUCCESS, cosmeticService.analyzeCosmetic(request));
    }
}
