package com.webeye.backend.disease.domain;

import com.webeye.backend.disease.domain.type.DiseaseType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private DiseaseType name;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiseaseNutrient> diseaseNutrients;
}