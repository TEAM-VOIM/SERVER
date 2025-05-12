package com.webeye.backend.rawmaterial.persistent;

import com.webeye.backend.rawmaterial.domain.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
    Optional<RawMaterial> findFirstByNameContaining(String name);
}
