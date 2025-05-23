package com.webeye.backend.cosmetic.domain;

import com.webeye.backend.global.domain.BaseEntity;
import com.webeye.backend.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CosmeticIngredient extends BaseEntity {

    @Id
    @Column(name = "cosmetic_ingredient_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cosmetic_id")
    private Cosmetic cosmetic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public CosmeticIngredient(
            Long id,
            Cosmetic cosmetic,
            Product product
    ) {
        this.id = id;
        this.cosmetic = cosmetic;
        this.product = product;
    }
}
