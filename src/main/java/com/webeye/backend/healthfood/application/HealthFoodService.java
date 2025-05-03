package com.webeye.backend.healthfood.application;

import com.webeye.backend.healthfood.domain.HealthFood;
import com.webeye.backend.healthfood.dto.HealthFoodResponse;
import com.webeye.backend.healthfood.infrastructure.client.HealthFoodClient;
import com.webeye.backend.healthfood.infrastructure.mapper.HealthFoodMapper;
import com.webeye.backend.healthfood.infrastructure.persistence.HealthFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthFoodService {

    @Value("${open-api.health-food.service-id}")
    private String serviceId;

    @Value("${open-api.health-food.service-key}")
    private String serviceKey;

    private final HealthFoodClient healthFoodClient;
    private final HealthFoodRepository healthFoodRepository;

    @Transactional
    public HealthFoodResponse.I2710 callHealthFoodApi() {
        HealthFoodResponse response = healthFoodClient.getHealthFood(serviceKey, serviceId, "json", "1", "478");

        HealthFoodResponse.I2710 i2710 = response.I2710();

        List<HealthFoodResponse.Row> rows = i2710.row();

        List<HealthFood> healthFoods = HealthFoodMapper.toEntityList(rows);

        return HealthFoodMapper.toResponseList(healthFoods, i2710.totalCount());
    }
}
