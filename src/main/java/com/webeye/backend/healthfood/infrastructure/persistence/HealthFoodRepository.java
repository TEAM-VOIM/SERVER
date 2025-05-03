package com.webeye.backend.healthfood.infrastructure.persistence;

import com.webeye.backend.healthfood.domain.HealthFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthFoodRepository extends JpaRepository<HealthFood, Long> {
}
