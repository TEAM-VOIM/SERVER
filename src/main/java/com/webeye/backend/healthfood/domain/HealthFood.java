package com.webeye.backend.healthfood.domain;

import com.webeye.backend.global.domain.BaseEntity;
import com.webeye.backend.healthfood.domain.type.Keyword;
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
    private String itemName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String functionality;

    @Column
    @Enumerated(EnumType.STRING)
    private Keyword keywords;

    @Builder
    public HealthFood(String itemName, String functionality, Keyword keywords) {
        this.itemName = itemName;
        this.functionality = functionality;
        this.keywords = keywords;
    }
}
