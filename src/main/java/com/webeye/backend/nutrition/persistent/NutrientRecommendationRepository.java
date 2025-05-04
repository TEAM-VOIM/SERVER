package com.webeye.backend.nutrition.persistent;

import com.webeye.backend.nutrition.domain.NutrientRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutrientRecommendationRepository extends JpaRepository<NutrientRecommendation, Long> {
}
