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
public class HealthFood extends BaseEntity {

    @Id
    @Column(name = "health_food_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String functionality;

    @OneToMany(mappedBy = "healthFood", cascade = CascadeType.ALL)
    private List<HealthFoodKeyword> keyword = new ArrayList<>();

    @Builder
    public HealthFood(String itemName, String functionality) {
        this.itemName = itemName;
        this.functionality = functionality;
    }

    public void addKeyword(HealthFoodKeyword healthFoodKeyword) {
        keyword.add(healthFoodKeyword);
        healthFoodKeyword.associateWithHealthFood(this);
    }
}
