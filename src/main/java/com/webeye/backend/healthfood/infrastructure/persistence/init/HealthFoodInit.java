package com.webeye.backend.healthfood.infrastructure.persistence.init;

import com.webeye.backend.global.util.DummyDataInit;
import com.webeye.backend.healthfood.domain.Keyword;
import com.webeye.backend.healthfood.domain.type.HealthFoodType;
import com.webeye.backend.healthfood.infrastructure.persistence.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@DummyDataInit
public class HealthFoodInit implements ApplicationRunner {

    private final KeywordRepository keywordRepository;

    @Override
    public void run(ApplicationArguments args) {
        for (HealthFoodType healthFoodType : HealthFoodType.values()) {
            if (keywordRepository.findByType(healthFoodType).isEmpty()) {
                Keyword keyword = Keyword.builder()
                        .type(healthFoodType)
                        .build();

                keywordRepository.save(keyword);
            }
        }
    }
}
