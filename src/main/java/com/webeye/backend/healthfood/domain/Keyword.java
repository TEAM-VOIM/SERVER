package com.webeye.backend.healthfood.domain;

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

    @Column(nullable = false, unique = true)
    private String keyword;

    @OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL)
    private List<HealthFoodKeyword> healthFoodKeywords = new ArrayList<>();

    @Builder
    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public void addHealthFood(HealthFoodKeyword healthFoodKeyword) {
        healthFoodKeywords.add(healthFoodKeyword);
        healthFoodKeyword.associateWithKeyword(this);
    }
}
