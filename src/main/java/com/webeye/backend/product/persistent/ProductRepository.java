package com.webeye.backend.product.persistent;

import com.webeye.backend.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsById(String id);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.review WHERE p.id = :id")
    Optional<Product> findByIdWithReview(@Param("id") String id);
}
