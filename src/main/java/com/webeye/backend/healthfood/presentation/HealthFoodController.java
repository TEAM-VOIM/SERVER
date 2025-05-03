package com.webeye.backend.healthfood.presentation;

import com.webeye.backend.global.dto.response.SuccessResponse;
import com.webeye.backend.healthfood.application.HealthFoodService;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import com.webeye.backend.healthfood.presentation.swagger.HealthFoodSwagger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.webeye.backend.global.dto.response.type.SuccessCode.HEALTH_FOOD_API_SUCCESS;

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
}
