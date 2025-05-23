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
public class Cosmetic extends BaseEntity {

    @Id
    @Column(name = "cosmetic_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", unique = true)
    private Product product;

    @Builder
    public Cosmetic(Long id, Product product) {
        this.id = id;
        this.product = product;
    }
}
