package com.webeye.backend.healthfood.infrastructure.persistence;

import com.webeye.backend.healthfood.domain.HealthFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HealthFoodRepository extends JpaRepository<HealthFood, Long> {
    @Query("SELECT h.itemName FROM HealthFood h")
    List<String> findAllItemNames();

    Optional<HealthFood> findByItemNameContaining(String itemName);
}
