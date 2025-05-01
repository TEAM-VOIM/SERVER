package com.webeye.backend.disease.persistent;

import com.webeye.backend.disease.domain.Disease;
import com.webeye.backend.disease.domain.type.DiseaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Optional<Disease> findByName(DiseaseType name);
}
