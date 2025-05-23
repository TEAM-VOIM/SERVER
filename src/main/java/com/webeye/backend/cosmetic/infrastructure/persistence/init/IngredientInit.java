package com.webeye.backend.cosmetic.infrastructure.persistence.init;

import com.webeye.backend.cosmetic.domain.Ingredient;
import com.webeye.backend.cosmetic.domain.type.IngredientType;
import com.webeye.backend.cosmetic.infrastructure.persistence.IngredientRepository;
import com.webeye.backend.global.util.DummyDataInit;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@DummyDataInit
public class IngredientInit implements ApplicationRunner {

    private final IngredientRepository ingredientRepository;

    @Builder
    public void run(ApplicationArguments args) {
        for (IngredientType ingredientType : IngredientType.values()) {
            if (ingredientRepository.findByIngredientType(ingredientType).isEmpty()) {
                Ingredient ingredient = Ingredient.builder()
                        .ingredientType(ingredientType)
                        .build();

                ingredientRepository.save(ingredient);
            }
        }
    }
}
