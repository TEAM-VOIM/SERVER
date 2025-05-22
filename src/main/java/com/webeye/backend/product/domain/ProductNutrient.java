package com.webeye.backend.product.domain;

import com.webeye.backend.global.domain.BaseEntity;
import com.webeye.backend.nutrition.domain.Nutrient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductNutrient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;

    @Column(nullable = false)
    private Double amount; // 영양소 함량

    @Builder
    public ProductNutrient(Product product, Nutrient nutrient, Double amount) {
        this.product = product;
        this.nutrient = nutrient;
        this.amount = amount;
    }

    public void associateWithProduct(Product product) {
        this.product = product;
    }
}