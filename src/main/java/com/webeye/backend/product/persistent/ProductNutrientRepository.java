package com.webeye.backend.product.persistent;

import com.webeye.backend.product.domain.ProductNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNutrientRepository extends JpaRepository<ProductNutrient, Long> {
}
