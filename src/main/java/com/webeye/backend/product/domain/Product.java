package com.webeye.backend.product.domain;

import com.webeye.backend.cosmetic.domain.CosmeticIngredient;
import com.webeye.backend.global.domain.BaseEntity;
import com.webeye.backend.product.domain.type.ProductType;
import com.webeye.backend.productdetail.domain.ProductDetail;
import com.webeye.backend.review.domain.Review;
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

    private Integer nutrientReferenceAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private ProductType productType;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Review review;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CosmeticIngredient> cosmeticIngredients = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAllergy> allergies = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductNutrient> nutrients = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductHealthfood> healthFoods = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductDetail> details = new ArrayList<>();

    @Builder
    public Product(
            String id,
            ProductType productType,
            Integer nutrientReferenceAmount
    ) {
        this.id = id;
        this.productType = productType;
        this.nutrientReferenceAmount = nutrientReferenceAmount;
    }

    public void associateWithReview(Review review) {
        this.review = review;
        if (review.getProduct() != this) {
            review.associateWithProduct(this);
        }
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

    public void addProductDetail(ProductDetail detail) {
        this.details.add(detail);
        detail.associateWithProduct(this);
    }

    public void addProductDetails(List<ProductDetail> details) {
        details.forEach(this::addProductDetail);
    }

    public void setNutrientReferenceAmount(Integer nutrientReferenceAmount) {
        this.nutrientReferenceAmount = nutrientReferenceAmount;
    }
}