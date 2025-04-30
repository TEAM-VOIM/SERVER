package com.webeye.backend.disease.domain;

import com.webeye.backend.nutrition.domain.Nutrient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class DiseaseNutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;
}
