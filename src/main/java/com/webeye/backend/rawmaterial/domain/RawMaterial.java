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

    @Column
    private String foodCd;

    @Column
    private String foodNm;

    @Column
    private String nutConSrtrQua;

    @Column
    private Double enerc;

    @Column
    private Double water;

    @Column
    private Double prot;

    @Column
    private Double fatce;

    @Column
    private Double ash;

    @Column
    private Double chocdf;

    @Column
    private Double sugar;

    @Column
    private Double fibtg;

    @Column
    private Double ca;

    @Column
    private Double fe;

    @Column
    private Double p;

    @Column
    private Double k;

    @Column
    private Double nat;

    @Column
    private Double vitaRae;

    @Column
    private Double retol;

    @Column
    private Double cartb;

    @Column
    private Double thia;

    @Column
    private Double ribf;

    @Column
    private Double nia;

    @Column
    private Double vitc;

    @Column
    private Double vitd;

    @Column
    private Double chole;

    @Column
    private Double fasat;

    @Column
    private Double fartn;

    @Builder
    private RawMaterial(Long id, String foodCd, String foodNm, String nutConSrtrQua, Double enerc, Double water, Double prot, Double fatce, Double ash,
                        Double chocdf, Double sugar, Double fibtg, Double ca, Double fe, Double p, Double k, Double nat, Double vitaRae, Double retol,
                        Double cartb, Double thia, Double ribf, Double nia, Double vitc, Double vitd, Double chole, Double fasat, Double fartn) {
        this.id = id;
        this.foodCd = foodCd;
        this.foodNm = foodNm;
        this.nutConSrtrQua = nutConSrtrQua;
        this.enerc = enerc;
        this.water = water;
        this.prot = prot;
        this.fatce = fatce;
        this.ash = ash;
        this.chocdf = chocdf;
        this.sugar = sugar;
        this.fibtg = fibtg;
        this.ca = ca;
        this.fe = fe;
        this.p = p;
        this.k = k;
        this.nat = nat;
        this.vitaRae = vitaRae;
        this.retol = retol;
        this.cartb = cartb;
        this.thia = thia;
        this.ribf = ribf;
        this.nia = nia;
        this.vitc = vitc;
        this.vitd = vitd;
        this.chole = chole;
        this.fasat = fasat;
        this.fartn = fartn;
    }
}