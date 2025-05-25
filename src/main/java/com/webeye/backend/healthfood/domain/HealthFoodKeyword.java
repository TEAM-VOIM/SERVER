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
public class HealthFoodKeyword extends BaseEntity {

    @Id
    @Column(name = "health_food_keyword_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_food_id")
    private HealthFood healthFood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @Builder
    public HealthFoodKeyword(HealthFood healthFood, Keyword keyword) {
        this.healthFood = healthFood;
        this.keyword = keyword;
    }
}
