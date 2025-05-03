package com.webeye.backend.nutrition.domain;

import com.webeye.backend.nutrition.domain.type.NutrientType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private NutrientType type;

    @Builder
    public Nutrient(NutrientType type) {
        this.type = type;
    }
}