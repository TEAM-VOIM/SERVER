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
        for (NutrientType nutrientType : NutrientType.values()) {
            if (nutrientRepository.findByType(nutrientType).isEmpty()) {
                Nutrient nutrient = Nutrient.builder()
                        .type(nutrientType)
                        .build();
                nutrientRepository.save(nutrient);
            }
        }
    }
}