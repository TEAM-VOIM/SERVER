package com.webeye.backend.product.domain;

import com.webeye.backend.allergy.type.AllergyType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductAllergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    private AllergyType allergy;

    @Builder
    public ProductAllergy(Product product, AllergyType allergy) {
        this.product = product;
        this.allergy = allergy;
    }

    public void setProduct(Product product) {
        this.product = product;
        product.getAllergies().add(this);
    }
}
