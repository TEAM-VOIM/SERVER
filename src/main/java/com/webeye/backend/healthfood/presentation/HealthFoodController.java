package com.webeye.backend.healthfood.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.healthfood.application.HealthFoodService;
import com.webeye.backend.healthfood.dto.HealthFoodAiResponse;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import com.webeye.backend.healthfood.presentation.swagger.HealthFoodSwagger;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.webeye.backend.global.dto.response.type.SuccessCode.HEALTH_FOOD_API_SUCCESS;
import static com.webeye.backend.global.dto.response.type.SuccessCode.HEALTH_FOOD_ANALYSIS_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/health-food")
public class HealthFoodController implements HealthFoodSwagger {

    private final HealthFoodService healthFoodService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public SuccessResponse<HealthFoodResponse.I2710> callHealthFoodApi() {
        return SuccessResponse.of(HEALTH_FOOD_API_SUCCESS, healthFoodService.callHealthFoodApi());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/keywords")
    public SuccessResponse<HealthFoodAiResponse> analyzeHealthFood(@Valid @RequestBody FoodProductAnalysisRequest request) {
        return SuccessResponse.of(HEALTH_FOOD_ANALYSIS_SUCCESS, healthFoodService.analyzeAndSave(request));
    }
}
