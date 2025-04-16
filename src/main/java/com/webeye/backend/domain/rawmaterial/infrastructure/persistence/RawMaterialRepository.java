package com.webeye.backend.domain.rawmaterial.infrastructure.persistence;

import com.webeye.backend.domain.rawmaterial.domain.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
