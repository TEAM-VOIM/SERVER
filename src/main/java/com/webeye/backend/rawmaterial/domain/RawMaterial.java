package com.webeye.backend.rawmaterial.domain;

import com.webeye.backend.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RawMaterial extends BaseEntity {

    @Id
    @Column(name = "raw_material_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double protein;  // 단백질

    private Double fat; // 지방

    private Double carbohydrate; // 탄수화물

    private Double sugars; // 당류

    private Double calcium; // 칼슘

    private Double phosphorus; // 인

    private Double sodium; // 나트륨

    private Double niacin; // 나이아신

    private Double cholesterol; // 콜레스테롤

    private Double saturatedFat; // 포화지방

    private Double transFat; // 트랜스지방

    @Builder
    private RawMaterial(
            String name,
            Double protein,
            Double fat,
            Double carbohydrate,
            Double sugars,
            Double calcium,
            Double phosphorus,
            Double sodium,
            Double niacin,
            Double cholesterol,
            Double saturatedFat,
            Double transFat
    ) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.sugars = sugars;
        this.calcium = calcium;
        this.phosphorus = phosphorus;
        this.sodium = sodium;
        this.niacin = niacin;
        this.cholesterol = cholesterol;
        this.saturatedFat = saturatedFat;
        this.transFat = transFat;
    }
}