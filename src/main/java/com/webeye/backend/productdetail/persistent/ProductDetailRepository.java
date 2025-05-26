package com.webeye.backend.productdetail.persistent;

import com.webeye.backend.productdetail.domain.ProductDetail;
import com.webeye.backend.productdetail.domain.type.OutlineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    Optional<ProductDetail> findByProductIdAndOutline(String productId, OutlineType outlineType);
}
