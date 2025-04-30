package com.webeye.backend.product.persistent;

import com.webeye.backend.product.domain.ProductAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAllergyRepository extends JpaRepository<ProductAllergy, Long> {
}

