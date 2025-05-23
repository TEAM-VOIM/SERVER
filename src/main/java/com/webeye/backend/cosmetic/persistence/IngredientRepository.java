package com.webeye.backend.cosmetic.persistence;

import com.webeye.backend.cosmetic.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
