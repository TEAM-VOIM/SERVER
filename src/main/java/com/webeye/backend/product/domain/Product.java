package com.webeye.backend.product.domain;

import com.webeye.backend.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Id
    @Column(name = "product_id", nullable = false)
    private String id; // 쿠팡에서 products 뒤에 오는 숫자

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAllergy> allergies = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductNutrient> nutrients = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductHealthfood> healthFoods = new ArrayList<>();

    @Builder
    public Product(String id) {
        this.id = id;
    }

    public void addNutrient(ProductNutrient nutrient) {
        this.nutrients.add(nutrient);
        nutrient.associateWithProduct(this);
    }

    public void addAllergy(ProductAllergy allergy) {
        this.allergies.add(allergy);
        allergy.associateWithProduct(this);
    }

    public void addHealthFood(ProductHealthfood healthFood) {
        this.healthFoods.add(healthFood);
        healthFood.associateWithProduct(this);
    }
}