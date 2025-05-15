package com.webeye.backend.product.domain;

import com.webeye.backend.global.domain.BaseEntity;
import com.webeye.backend.healthfood.domain.HealthFood;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductHealthfood extends BaseEntity {

    @Id
    @Column(name = "product_health_food_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_food_id")
    private HealthFood healthFood;

    @Builder
    public ProductHealthfood(Product product, HealthFood healthFood) {
        this.product = product;
        this.healthFood = healthFood;
    }

    public void associateWithProduct(Product product) {
        this.product = product;
        product.getHealthFoods().add(this);
    }

    public void associateWithHealthFood(HealthFood healthFood) {
        this.healthFood = healthFood;
        healthFood.getHealthfoods().add(this);
    }
}
