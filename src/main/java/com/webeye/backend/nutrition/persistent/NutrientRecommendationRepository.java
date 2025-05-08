package com.webeye.backend.nutrition.persistent;

import com.webeye.backend.nutrition.domain.Nutrient;
import com.webeye.backend.nutrition.domain.NutrientRecommendation;
import com.webeye.backend.nutrition.domain.type.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutrientRecommendationRepository extends JpaRepository<NutrientRecommendation, Long> {

    @Query("SELECT r FROM NutrientRecommendation r " +
            "WHERE r.nutrient.id = :nutrientId " +
            "AND r.gender = :gender " +
            "AND :age BETWEEN r.minAge AND r.maxAge")
    Optional<NutrientRecommendation> findByNutrientIdAndGenderAndAge(
            @Param("nutrientId") Long nutrientId,
            @Param("gender") Gender gender,
            @Param("age") int age);
}
