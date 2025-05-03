package com.webeye.backend.healthfood.domain;

import com.webeye.backend.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthFood extends BaseEntity {

    @Id
    @Column(name = "health_food_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String functionality;

    @Column
    @Enumerated(EnumType.STRING)
    private Keyword keyword;

    @Builder
    public HealthFood(String name, String functionality, Keyword keyword) {
        this.name = name;
        this.functionality = functionality;
        this.keyword = keyword;
    }
}
