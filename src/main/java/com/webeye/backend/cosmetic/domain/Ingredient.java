package com.webeye.backend.cosmetic.domain;

import com.webeye.backend.cosmetic.domain.type.IngredientType;
import com.webeye.backend.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient extends BaseEntity {

    @Id
    @Column(name = "ingredient_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private IngredientType ingredientType;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CosmeticIngredient> cosmeticIngredients = new ArrayList<>();

    @Builder
    public Ingredient(
            Long id,
            IngredientType ingredientType
    ) {
        this.id = id;
        this.ingredientType = ingredientType;
    }
}
