package com.webeye.backend.disease.persistent;

import com.webeye.backend.disease.domain.DiseaseNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseNutrientRepository extends JpaRepository<DiseaseNutrient, Long> {
}
