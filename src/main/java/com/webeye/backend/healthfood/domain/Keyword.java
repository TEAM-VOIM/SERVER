package com.webeye.backend.healthfood.domain;

import com.webeye.backend.healthfood.domain.type.HealthFoodType;
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
public class Keyword {

    @Id
    @Column(name = "keyword_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private HealthFoodType type;

    @OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL)
    private List<HealthFoodKeyword> healthFoodKeywords = new ArrayList<>();

    @Builder
    public Keyword(HealthFoodType type) {
        this.type = type;
    }
}
