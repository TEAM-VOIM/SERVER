package com.webeye.backend.rawmaterial.infrastructure.persistence;

import com.webeye.backend.rawmaterial.domain.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
}
