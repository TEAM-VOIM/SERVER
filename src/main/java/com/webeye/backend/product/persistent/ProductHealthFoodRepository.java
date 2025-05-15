package com.webeye.backend.product.persistent;

import com.webeye.backend.product.domain.ProductHealthfood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHealthFoodRepository extends JpaRepository<ProductHealthfood, Long> {
}
