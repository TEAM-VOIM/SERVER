package com.webeye.backend.product.persistent.init;

import com.webeye.backend.global.util.DummyDataInit;
import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import com.webeye.backend.nutrition.persistent.NutrientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@DummyDataInit
public class NutrientInit implements ApplicationRunner {
    private final NutrientRepository nutrientRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (nutrientRepository.count() >= NutrientType.values().length) {
            log.info("[Nutrient] 기본 데이터 존재");
            return;
        }
        for (NutrientType nutrientType : NutrientType.values()) {
            Nutrient nutrient = Nutrient.builder()
                    .name(nutrientType)
                    .build();
            nutrientRepository.save(nutrient);
        }
        log.info("[Nutrient] 기본 데이터 저장 완료");
    }
}
