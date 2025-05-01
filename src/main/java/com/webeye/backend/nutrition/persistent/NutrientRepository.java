package com.webeye.backend.nutrition.persistent;

import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.type.NutrientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
    Optional<Nutrient> findByName(NutrientType type);
}
