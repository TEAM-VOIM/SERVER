package com.webeye.backend.nutrition.domain;

import com.webeye.backend.global.domain.BaseEntity;
import com.webeye.backend.nutrition.domain.type.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NutrientRecommendation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrient_id", nullable = false)
    private Nutrient nutrient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private int minAge;

    @Column(nullable = false)
    private int maxAge;

    @Column(nullable = false)
    private double amount;

    @Builder
    public NutrientRecommendation(
            Nutrient nutrient,
            Gender gender,
            int minAge,
            int maxAge,
            double amount
    ) {
        this.nutrient = nutrient;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.amount = amount;
    }
}