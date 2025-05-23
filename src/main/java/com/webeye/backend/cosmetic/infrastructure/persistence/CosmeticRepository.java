package com.webeye.backend.cosmetic.infrastructure.persistence;

import com.webeye.backend.cosmetic.domain.Cosmetic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CosmeticRepository extends JpaRepository<Cosmetic, Long> {
}
