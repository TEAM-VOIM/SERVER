package com.webeye.backend.healthfood.infrastructure.persistence;

import com.webeye.backend.healthfood.domain.HealthFoodKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthFoodKeywordRepository extends JpaRepository<HealthFoodKeyword, Long> {
}
