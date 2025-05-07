package com.webeye.backend.healthfood.domain;

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
public class Keyword extends BaseEntity {

    @Id
    @Column(name = "keyword_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String keyword;

    @OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL)
    private List<HealthFoodKeyword> healthFood = new ArrayList<>();

    @Builder
    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public void addHealthFood(HealthFoodKeyword healthFoodKeyword) {
        healthFood.add(healthFoodKeyword);
        healthFoodKeyword.associateWithKeyword(this);
    }
}
