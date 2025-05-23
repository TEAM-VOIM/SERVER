package com.webeye.backend.cosmetic.persistence;

import com.webeye.backend.cosmetic.domain.Ingredient;
import com.webeye.backend.cosmetic.domain.type.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByIngredientType(IngredientType ingredientType);
}
