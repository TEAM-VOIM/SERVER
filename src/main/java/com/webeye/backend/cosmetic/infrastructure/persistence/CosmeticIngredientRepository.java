package com.webeye.backend.cosmetic.infrastructure.persistence;

import com.webeye.backend.cosmetic.domain.CosmeticIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CosmeticIngredientRepository extends JpaRepository<CosmeticIngredient, Long> {
}
